package zhouyang.juc.thread;

import java.util.concurrent.TimeUnit;

/**
 * Interrupt 中断状态   线程正常运行则改变中断状态  线程休眠则中断休眠不改状态
 * interrrupted 判断中断状态重置状态
 * isInterrupted 判断中断状态不充值状态
 */
public class InterruptDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //如果刚好在睡眠的时候中断则直接唤醒 并且中断状态并不改变
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
