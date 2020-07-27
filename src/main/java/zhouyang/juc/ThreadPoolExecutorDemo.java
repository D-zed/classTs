package zhouyang.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {

    /**
     * （1）CPU密集型：
     * <p>
     *        定义：CPU密集型的意思就是该任务需要大量运算，而没有阻塞，CPU一直全速运行。
     *        CPU密集型任务只有在真正的多核CPU上才可能得到加速（通过多线程）。
     *        CPU密集型任务配置尽可能少的线程数。
     *        CPU密集型线程数配置公式：(CPU核数+1)个线程的线程池
     * <p>
     * （2）IO密集型：
     * <p>
     *        定义：IO密集型，即该任务需要大量的IO，即大量的阻塞。
     *        在单线程上运行IO密集型任务会导致浪费大量的CPU运算能力浪费在等待。
     *        所以IO密集型任务中使用多线程可以大大的加速程序运行，即使在单核CPU上，这种加速主要利用了被浪费掉的阻塞时间。
     *       
     *      
     *        
     */

    public static void main(String[] args) throws InterruptedException {
        //首先线程池顶层接口是这个
        Executor executor;
        //然后是这个
        ExecutorService executorService;
        //然后是其实现类
        ThreadPoolExecutor threadPoolExecutor;
        //线程池工具类 类比 Collections Arrays
        Executors executors;
        //以下三个对应的是线程池的默认实现，然而都不能用  因为要不就是队列无限 或者 最大线程数无线会出大问题
        Executors.newSingleThreadExecutor();
        Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(4);

        //平时工作中只允许这个线程池
        //corePoolSize 核心线程数
        //maximumPoolSize 最大线程数
        //keepAliveTime 线程存活时间 线程空闲下来到销毁的时间
        //unit 时间单位
        //workQueue  线程阻塞队列
        //factory  创建线程池线程的工厂一般就默认就可以了
        //rejectHandler //拒绝策略 1默认终端线程抛出异常 2disCard直接丢弃无感知 3线程池满了之后就将任务还给主线程 4丢弃最旧的线程
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors() + 1, 2, TimeUnit.SECONDS, new LinkedBlockingQueue(5),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


        //这个并发编程实战上推荐
        //cpu密集型 服务器的核数+1作为最大线程数 ，服务器的核数作为核心线程数
        //io密集型的 因为过多的io会造成阻塞，甚至造成cpu空闲所以开多个线程可以更好的体现多核cpu的优势
        //当然以上只是经验数字，更精确的判断还得实际的压测决定
        System.out.println("获得系统的核数 :" + Runtime.getRuntime().availableProcessors());
        CountDownLatch countDownLatch = new CountDownLatch(29);
        //按理说能够同时并发的线程数是 最大线程数+阻塞队列数，并且是阻塞队列满了的时候才会扩容到最大线程数

        //线程池的执行方法 https://www.cnblogs.com/by-my-blog/p/10779333.html
        //execute 是执行runable的 submit是执行 callable和runable的 这个有返回值可以get 并且可以获取线程的执行状态
        for (int i = 1; i <= 30; i++) {
            try {
                threadPool.execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {

                    }
                    System.out.println(Thread.currentThread().getName() + "-->");
                    countDownLatch.countDown();
                });
            } catch (Exception e) {
                System.out.println("error" + e.getMessage());
                int corePoolSize = threadPool.getCorePoolSize();
                int activeCount = threadPool.getActiveCount();
                int maximumPoolSize = threadPool.getMaximumPoolSize();
                threadPool.setCorePoolSize(corePoolSize + 10);
                threadPool.setMaximumPoolSize(maximumPoolSize + 10);
                System.out.println("activeCount-->" + activeCount);
            }
        }
        countDownLatch.await();

        //线程池如何优雅推迟
        //线程的终断用的是 interrupt的时候是改变线程状态 当线程处于阻塞状态则直接抛出异常并且终断
        threadPool.shutdown();
        //同步等待线程池的结束
        threadPool.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("结束");
    }
}




