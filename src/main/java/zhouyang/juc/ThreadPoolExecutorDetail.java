package zhouyang.juc;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 并发编程之美中的案例
 * 240页
 *
 * 线程池的最终实现逻辑是使用了 worker 队列 并且使用核心线程数和最大线程数 以及阻塞队列对任务进行处理 最终还是交给真正的线程从任务队列中获取
 *
 * 问题记录  当我们的线程worker在 拿到task执行之后 就跳出了循环了 那么新任务来了的时候这线程又是如何去任务队列中获取任务的呢
 * @author dzd
 */
public class ThreadPoolExecutorDetail {

    public static void main(String[] args) throws InterruptedException {
/*        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors() + 1, 2, TimeUnit.SECONDS, new LinkedBlockingQueue(5),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());*/

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 7, 10, TimeUnit.SECONDS, new LinkedBlockingQueue(8),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i <15 ; i++) {
            threadPool.execute(() -> {
                System.out.println("ddd");
            });
        }
        /**
         * 经看过源码之后发现 初始话核心线程的那一刹那 整个线程池能够并发处理的 execute 任务 为 最大线程数 + 阻塞队列数
         * 然后后续再来任务就完全取决于 任务队列的大小了 也就是阻塞队列的大小，因为任务提交的runnable实现 都会被封装成 worker类 并且在各个已经存活的线程中对任务队列进行轮询操作
         * 然而线程第一次初始化的时候会把本任务封装到worker中 所以这时候执行任务无需从任务队列中获取 所以也即是 最大线程数+任务队列数的大小了
         *
         * 比如以下示例初始时候可以容下15个任务 后边只能八个了
         */
        TimeUnit.SECONDS.sleep(3);
        for (int i = 0; i <8 ; i++) {
            threadPool.execute(() -> {
                System.out.println("cccc");
            });
        }


       /**
        * 线程池读execute的实现
        * public void execute(Runnable command) {
        *    （1）
            if (command == null)
                throw new NullPointerException();
        //   （2）获取线程池的状态
            int c = ctl.get();
             （3）如果当前线程池中的线程个数小于corePoolSize，会向队列中新增一个核心线程
                 执行该任务
            if (workerCountOf(c) < corePoolSize) {
                if (addWorker(command, true))
                    return;
                c = ctl.get();
            }
            （4）如果当前线程池是处于running状态则添加当前的任务到任务队列
             //如果已经是非Running状态了则需要抛弃任务
            if (isRunning(c) && workQueue.offer(command)) {
               （4.1）
                int recheck = ctl.get();
                （4.2） 添加任务之后再次校验当前的线程池状态，如果此时没有running
                 则移除任务，并且执行拒绝策略
                if (! isRunning(recheck) && remove(command))
                    reject(command);
                 （4.3）如果当前线程池是running 则再次判断当前线程池是否
                还有线程，如果没有了则添加一个
                else if (workerCountOf(recheck) == 0)
                    addWorker(null, false);
            }
            （5）如果步骤四的添加队列失败了
           则继续创建线程 当然如果此时大于了最大线程数了 则执行拒绝策略
            else if (!addWorker(command, false))
                reject(command);
        }*/

        /**
         * 以下分析addWorker代码
         * private boolean addWorker(Runnable firstTask, boolean core) {
         *         retry:
         *         for (;;) {
         *             int c = ctl.get();
         *             int rs = runStateOf(c);
         *
         *             // （6） 当以下几种状态的时候则直接返回失败
         *             if (rs >= SHUTDOWN &&
         *                 ! (rs == SHUTDOWN &&
         *                    firstTask == null &&
         *                    ! workQueue.isEmpty()))
         *                 return false;
         *             //（7）
         *             for (;;) {
         *
         *                 int wc = workerCountOf(c);
         *                 //（7.1） 如果当前的线程个数超限，则返回false
         *                 if (wc >= CAPACITY ||
         *                     wc >= (core ? corePoolSize : maximumPoolSize))
         *                     return false;
         *                 //（7.2） 如果当前线程个数不超限制 cas操作设置线程个数
         *                 if (compareAndIncrementWorkerCount(c))
         *                     break retry;
         *                 // （7.3）查看当前线程池的状态是否有变化，如果变了则重新进入外循环
         *                 c = ctl.get();  // Re-read ctl
         *                 //
         *                 if (runStateOf(c) != rs)
         *                     continue retry;
         *                 //否则进入内循环，这个地方主要就是要cas添加线程池的
         *             }
         *         }
         *         //（8）到这里说明已经成功添加的线程池个数
         *         boolean workerStarted = false;
         *         boolean workerAdded = false;
         *         Worker w = null;
         *         try {
         *             //（8.1）这时要船舰真正的worker（worker实现了runnable 和 aqs）
         *             w = new Worker(firstTask);
         *             final Thread t = w.thread;
         *             if (t != null) {
         *                 final ReentrantLock mainLock = this.mainLock;
         *                 //（8.2）防止多个线程调用了线程池的execute
         *                 mainLock.lock();
         *                 try {
         *                     //（8.3）重新检查防止获取锁前调用了shutdown方法
         *                     int rs = runStateOf(ctl.get());
         *
         *                     if (rs < SHUTDOWN ||
         *                         (rs == SHUTDOWN && firstTask == null)) {
         *                         if (t.isAlive()) // precheck that t is startable
         *                             throw new IllegalThreadStateException();
         *                         //（8.4）添加真正的任务
         *                         workers.add(w);
         *                         int s = workers.size();
         *                         if (s > largestPoolSize)
         *                             largestPoolSize = s;
         *                         workerAdded = true;
         *                     }
         *                 } finally {
         *                     mainLock.unlock();
         *                 }
         *                 //（8.5）添加成功之后需要启动任务  启动任务中也就是启动了线程  并且 其中调用的是  runWorker()
         *                 if (workerAdded) {
         *                     t.start();
         *                     workerStarted = true;
         *                 }
         *             }
         *         } finally {
         *             if (! workerStarted)
         *                 addWorkerFailed(w);
         *         }
         *         return workerStarted;
         *     }
         */


        /**
         * final void runWorker(Worker w) {
         *         Thread wt = Thread.currentThread();
         *         Runnable task = w.firstTask;
         *         w.firstTask = null;
         *         //(9)
         *         w.unlock();
         *         boolean completedAbruptly = true;
         *         try {
         *         // (10) 线程启动之后需要执行的任务其实在worker中 当达到核心线程数的时候就不会再创建新的线程了 而这几个核心线程会从 workers中获取不停的执行
         *              //此处首先判断自己对应的任务有没有完成 ，如果本worker的任务已经完成  那么就去判断阻塞队列从阻塞队列中弹出poll
         *             while (task != null || (task = getTask()) != null) {
         *             //(10.1)
         *                 w.lock();
         *                 if ((runStateAtLeast(ctl.get(), STOP) ||
         *                      (Thread.interrupted() &&
         *                       runStateAtLeast(ctl.get(), STOP))) &&
         *                     !wt.isInterrupted())
         *                     wt.interrupt();
         *                 try {
         *                 //(10.2)
         *                     beforeExecute(wt, task);
         *                     Throwable thrown = null;
         *                     try {
         *                         //(10.3)
         *                         task.run();
         *                     } catch (RuntimeException x) {
         *                         thrown = x; throw x;
         *                     } catch (Error x) {
         *                         thrown = x; throw x;
         *                     } catch (Throwable x) {
         *                         thrown = x; throw new Error(x);
         *                     } finally {
         *                     //(10.4)
         *                         afterExecute(task, thrown);
         *                     }
         *                 } finally {
         *                     task = null;
         *                     //(10.5)
         *                     w.completedTasks++;
         *                     w.unlock();
         *                 }
         *             }
         *             completedAbruptly = false;
         *         } finally {
         *         // (11)
         *             processWorkerExit(w, completedAbruptly);
         *         }
         *     }
         */


        /**
         *  上面的代码 11
         private void processWorkerExit(Worker w, boolean completedAbruptly) {
         if (completedAbruptly)
         decrementWorkerCount();
         //（11.1）
         final ReentrantLock mainLock = this.mainLock;
         mainLock.lock();
         try {
         //统计整个线程池中完成的任务个数
         completedTaskCount += w.completedTasks;
         //从任务set中移除当前的完成的任务
         workers.remove(w);
         } finally {
         mainLock.unlock();
         }
         //（11.2） 检验当前线程池是否为终止状态 尝试设置
         tryTerminate();
         //（11.3）
         int c = ctl.get();
         //如果当前线程个数小于核心线程数则增加
         if (runStateLessThan(c, STOP)) {
         if (!completedAbruptly) {
         int min = allowCoreThreadTimeOut ? 0 : corePoolSize;
         if (min == 0 && ! workQueue.isEmpty())
         min = 1;
         if (workerCountOf(c) >= min)
         return; // replacement not needed
         }
         addWorker(null, false);
         }
         }

         */

        /**
         *   final void tryTerminate() {
         *         for (;;) {
         *             int c = ctl.get();
         *             if (isRunning(c) ||
         *                 runStateAtLeast(c, TIDYING) ||
         *                 (runStateOf(c) == SHUTDOWN && ! workQueue.isEmpty()))
         *                 return;
         *             if (workerCountOf(c) != 0) { // Eligible to terminate
         *                 interruptIdleWorkers(ONLY_ONE);
         *                 return;
         *             }
         *
         *             final ReentrantLock mainLock = this.mainLock;
         *             mainLock.lock();
         *             try {
         *                 if (ctl.compareAndSet(c, ctlOf(TIDYING, 0))) {
         *                     try {
         *                         terminated();
         *                     } finally {
         *                         ctl.set(ctlOf(TERMINATED, 0));
         *                         //唤醒所有等待中的条件队列
         *                         termination.signalAll();
         *                     }
         *                     return;
         *                 }
         *             } finally {
         *                 mainLock.unlock();
         *             }
         *             // else retry on failed CAS
         *         }
         *     }
         */


    }
}
