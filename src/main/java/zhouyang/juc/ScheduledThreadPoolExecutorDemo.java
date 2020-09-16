package zhouyang.juc;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 继承了ThreadPoolExecutor 并且 实现了ScheduledExecutorService接口、
 *
 * unsafe类的一些东西
 * https://www.cnblogs.com/chenyangyao/p/5269622.html
 * @author dzd
 */
public class ScheduledThreadPoolExecutorDemo {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor=new ScheduledThreadPoolExecutor(11);

        scheduledThreadPoolExecutor.schedule(()->{
            System.out.println("吃了么");
        },3, TimeUnit.SECONDS);

        /**
         * 此为 继承了Delayed  FutureTask 的类 所以也即是 优先队列 和 可以适配 callable
         *ScheduledFutureTask(Runnable r, V result, long ns){
         *super(r, result);
         *this.time = ns;
         *this.period = 0;  这个参数如果是 0 则代表一次性任务 如果大于0则是 固定频率的任务  小于0则是固定延迟的任务
         *this.sequenceNumber = sequencer.getAndIncrement();
         *}
         */
        /**
         * public ScheduledFuture<?> schedule(Runnable command,
         *                                        long delay,
         *                                        TimeUnit unit) {
         *         if (command == null || unit == null)
         *             throw new NullPointerException();
         *            // （2） 任务转换 将runnable转为callable 并且封装到FutureTask
         *         RunnableScheduledFuture<?> t = decorateTask(command,
         *             new ScheduledFutureTask<Void>(command, null,
         *                                            triggerTime(delay, unit)));
         *          //（3） 添加任务到延迟队列
         *         delayedExecute(t);
         *         return t;
         *     }
         *
         *
         *     因为此线程池是 常规线程池的子类并且在构造的时候就给常规线程池的任务队列指定成了 延迟任务队列
         *     在执行  delayedExecute 任务之后
         *     void ensurePrestart() {
         *         int wc = workerCountOf(ctl.get());
         *         if (wc < corePoolSize)
         *             addWorker(null, true);
         *         else if (wc == 0)
         *             addWorker(null, false);
         *     }
         *
         *     最终核心调用还是  addWorker 方法触发线程池 并且使用的是 take方法阻塞执行任务，并且任务已经按照时间排列好了
         */

        /**
         * 当我们是一次性任务的时候调用的是 super的 run
         * public void run() {
         *         //（12）
         *         if (state != NEW ||
         *             !UNSAFE.compareAndSwapObject(this, runnerOffset,
         *                                          null, Thread.currentThread()))
         *             return;
         *         //（13）
         *         try {
         *             Callable<V> c = callable;
         *             if (c != null && state == NEW) {
         *                 V result;
         *                 boolean ran;
         *                 try {
         *                     result = c.call();
         *                     ran = true;
         *                 } catch (Throwable ex) {
         *                     result = null;
         *                     ran = false;
         *                     //（13.1）
         *                     setException(ex);
         *                 }
         *                 //（13.2）
         *                 if (ran)
         *                     set(result);
         *             }
         *         } finally {
         *             // runner must be non-null until state is settled to
         *             // prevent concurrent calls to run()
         *             runner = null;
         *             // state must be re-read after nulling runner to prevent
         *             // leaked interrupts
         *             int s = state;
         *             if (s >= INTERRUPTING)
         *                 handlePossibleCancellationInterrupt(s);
         *         }
         *     }
         *
         *
         *     futuretask的run方法中为什么用 cas操作判断
         *     将此未来的结果设置为给定值，除非*该未来已被设置或被取消。 * * <p>在成功完成计算后，此方法由{@link #run}方法在内部调用。 * * @参数v值
         *     这个地方按理说是不会有并发的，但是防止外部线程将其状态改变则会出现问题
         *     书 251页 pdf 266页的解释有误
         *     protected void set(V v) {
         *         if (UNSAFE.compareAndSwapInt(this, stateOffset, NEW, COMPLETING)) {
         *             outcome = v;
         *             UNSAFE.putOrderedInt(this, stateOffset, NORMAL); // final state
         *             finishCompletion();
         *         }
         *     }
         *
         *     run方法执行完毕
         *     private void finishCompletion() {
         *         //
         *         for (WaitNode q; (q = waiters) != null;) {
         *             if (UNSAFE.compareAndSwapObject(this, waitersOffset, q, null)) {
         *                 for (;;) {
         *                     Thread t = q.thread;
         *                     if (t != null) {
         *                         q.thread = null;
         *                         LockSupport.unpark(t);
         *                     }
         *                     WaitNode next = q.next;
         *                     if (next == null)
         *                         break;
         *                     q.next = null; // unlink to help gc
         *                     q = next;
         *                 }
         *                 break;
         *             }
         *         }
         *
         *         done();
         *
         *         callable = null;        // to reduce footprint
         *     }
         *
         *
         *
         *     get阻塞式获取的关键代码
         *      private int awaitDone(boolean timed, long nanos)
         *         throws InterruptedException {
         *         final long deadline = timed ? System.nanoTime() + nanos : 0L;
         *         WaitNode q = null;
         *         boolean queued = false;
         *         for (;;) {
         *             if (Thread.interrupted()) {
         *                 removeWaiter(q);
         *                 throw new InterruptedException();
         *             }
         *
         *             int s = state;
         *             if (s > COMPLETING) {
         *                 if (q != null)
         *                     q.thread = null;
         *                 return s;
         *             }
         *             else if (s == COMPLETING) // cannot time out yet
         *                 Thread.yield();
         *             else if (q == null)
         *                 q = new WaitNode();
         *             else if (!queued)
         *                 queued = UNSAFE.compareAndSwapObject(this, waitersOffset,
         *                                                      q.next = waiters, q);
         *             else if (timed) {
         *                 nanos = deadline - System.nanoTime();
         *                 if (nanos <= 0L) {
         *                     removeWaiter(q);
         *                     return state;
         *                 }
         *                 LockSupport.parkNanos(this, nanos);
         *             }
         *             else
         *                 LockSupport.park(this);
         *         }
         *     }
         */
    }
}
