package zhouyang.juc.futureproblem;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * FutureTask的问题复现
 * 当 拒绝策略为丢弃的时候使用了 get获取执行后的结果 最造成一直阻塞  因为 在没有确定任务会不会被丢弃的时候 FutureTask就已经进行了阻塞
 * 然后 等待着任务执行完成并且唤醒，但是任务就这样无声无息的没有开始 所以导致了这种阻塞
 * @author dzd
 */
public class FutureProblem {


    private final static ThreadPoolExecutor executorOne=
            new ThreadPoolExecutor(1,1,1, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(1),new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future futureOne = executorOne.submit(() -> {
            System.out.println("start runable one");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Future futureTwo = executorOne.submit(() -> {

            System.out.println("start runable two");
        });

        Future futureThree=null;
        try {
            futureThree=    executorOne.submit(()->{
                System.out.println("start runable three");
            });
        }catch (Exception e){
            e.printStackTrace();
        }


        System.out.println("task one "+futureOne.get());
        System.out.println("task two "+futureTwo.get());
        System.out.println("task three "+futureThree==null?null:futureThree.get());

        executorOne.shutdown();
    }
}
