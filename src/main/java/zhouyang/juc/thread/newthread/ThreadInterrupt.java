package zhouyang.juc.thread.newthread;

import java.util.concurrent.TimeUnit;

/**
 * Interrupted 重置中断状态
 * 在执行的操作中会发现 有一次true 然后后边全是false 所以代表Interrupted会擦除中断标志
 * @author dzd
 */
public class ThreadInterrupt {

    public static void main(String[] args) {

        System.out.println("首先查看主线程的状态并且重置标志位 "+Thread.interrupted());
        //设置当前的标志位为中断 设置则代表非正常醒来 不设置代表正常醒来
        Thread.currentThread().interrupt();
        System.out.println("获取当前线程状态不重置标志位"+Thread.currentThread().isInterrupted());

        try {
            System.out.println("睡");
            //
            TimeUnit.SECONDS.sleep(3);
            System.out.println("正常醒来的标志位"+Thread.currentThread().isInterrupted());
        }catch (InterruptedException e){
            System.out.println("睡中醒来擦除标志位");
            System.out.println("都要中断了 还睡什么"+Thread.currentThread().isInterrupted());
        }



    }
}