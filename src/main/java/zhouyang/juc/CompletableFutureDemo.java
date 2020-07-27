package zhouyang.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("吃了么");
        });
        //加上这个可以让异步线程等待阻塞，否则就相当于令开了一个线程
        // completableFuture.get();

        System.out.println("没吃");
    }
}
