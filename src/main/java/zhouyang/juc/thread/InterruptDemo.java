package zhouyang.juc.thread;

import java.util.concurrent.TimeUnit;

/**
 * Interrupt 中断状态
 * interrrupted 判断中断状态重置状态
 * isInterrupted 判断中断状态不充值状态
 */
public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()){

                System.out.println("我没中断---"+Thread.currentThread().isInterrupted());
            }
        });
        thread.start();

        TimeUnit.SECONDS.sleep(3);

        System.out.println("中断线程");

        thread.interrupt();

        thread.join();

        System.out.println("main over");


    }
}
