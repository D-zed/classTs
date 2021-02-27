package zhouyang.thread;

import java.util.concurrent.TimeUnit;

/**
 * Interrupt 中断状态
 * interrrupted 判断中断状态重置状态
 * isInterrupted 判断中断状态不重置状态
 */
public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()){

                System.out.println("我没中断---"+Thread.currentThread().isInterrupted());
                System.out.println("我没中断2---"+Thread.interrupted());

            }
        });
        thread.start();

        TimeUnit.SECONDS.sleep(100);

        System.out.println("中断线程");

        thread.interrupt();

        thread.join();

        System.out.println("main over");


    }
}
