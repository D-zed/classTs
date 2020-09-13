package zhouyang.juc;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 并发编程之美中的案例
 * 240页
 * @author dzd
 */
public class ThreadPoolExecutorDetail {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors() + 1, 2, TimeUnit.SECONDS, new LinkedBlockingQueue(5),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

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
         *  todo 目前到 235页
         *             // （6）
         *             if (rs >= SHUTDOWN &&
         *                 ! (rs == SHUTDOWN &&
         *                    firstTask == null &&
         *                    ! workQueue.isEmpty()))
         *                 return false;
         *             //（7）
         *             for (;;) {
         *
         *                 int wc = workerCountOf(c);
         *                 //（7.1）
         *                 if (wc >= CAPACITY ||
         *                     wc >= (core ? corePoolSize : maximumPoolSize))
         *                     return false;
         *                 //（7.2）
         *                 if (compareAndIncrementWorkerCount(c))
         *                     break retry;
         *                 // （7.3）
         *                 c = ctl.get();  // Re-read ctl
         *                 if (runStateOf(c) != rs)
         *                     continue retry;
         *                 // else CAS failed due to workerCount change; retry inner loop
         *             }
         *         }
         *         //（8）
         *         boolean workerStarted = false;
         *         boolean workerAdded = false;
         *         Worker w = null;
         *         try {
         *             //（8.1）
         *             w = new Worker(firstTask);
         *             final Thread t = w.thread;
         *             if (t != null) {
         *                 final ReentrantLock mainLock = this.mainLock;
         *                 //（8.2）
         *                 mainLock.lock();
         *                 try {
         *                     //（8.3）
         *                     int rs = runStateOf(ctl.get());
         *
         *                     if (rs < SHUTDOWN ||
         *                         (rs == SHUTDOWN && firstTask == null)) {
         *                         if (t.isAlive()) // precheck that t is startable
         *                             throw new IllegalThreadStateException();
         *                         //（8.4）
         *                         workers.add(w);
         *                         int s = workers.size();
         *                         if (s > largestPoolSize)
         *                             largestPoolSize = s;
         *                         workerAdded = true;
         *                     }
         *                 } finally {
         *                     mainLock.unlock();
         *                 }
         *                 //（8.5）
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
        threadPool.execute(() -> {

        });
    }
}
