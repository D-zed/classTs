package zhouyang.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompatableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "start");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "没有返回值异步执行");
        });
        System.out.println("上面的线程执行完了么1");
        //创建一个线程异步调用
        completableFuture.get();

        System.out.println("上面的线程执行完了么2");

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "有返回值");
          int a=  10/0;
            return 1024;
        });

        integerCompletableFuture.whenComplete((t,u)->{
            System.out.println(t+"--t----u--"+u);
        }).exceptionally(f->{
            System.out.println("-----exception"+f.getMessage());
            return 222;
        });
        //异步调用获得返回值
        Integer integer = integerCompletableFuture.get();
        System.out.println(integer);

    }
}
