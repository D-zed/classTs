package zhouyang.juc.futureproblem;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * FutureTask的问题的解决方案
 * 由于其中的原因是我们的 get方法会判断futureTask的状态，只有达到了 complete 状态才可以重新唤醒 所以只要我们自定义一个 拒绝策略即可
 * @author dzd
 */
public class FutureProblemResolve {


    private final static ThreadPoolExecutor executorOne=
            new ThreadPoolExecutor(1,1,1, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(1),new MyRejectedExecutionHandler());

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
        try {
            System.out.println("task three "+futureThree==null?null:futureThree.get());
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("结束了");
        executorOne.shutdown();
    }
}
