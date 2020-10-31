package zhouyang.juc.thread.newthread;

import java.util.concurrent.TimeUnit;

/**
 * Interrupted 重置中断状态
 * 在执行的操作中会发现 有一次true 然后后边全是false 所以代表Interrupted会擦除中断标志
 * @author dzd
 */
public class ThreadInterrupted {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.interrupted());
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        //短暂停顿确保线程已经启动
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();
    }
}