package zhouyang.thread.newthread;

import java.util.concurrent.TimeUnit;

/**
 * isInterrupted 不会重置状态
 * @author dzd
 */
public class ThreadisInterrupted {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(4);
                    } catch (InterruptedException e) {
                        System.out.println("thread is interrupted "+this.isInterrupted());
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);

        System.out.println("thread is interrupted "+thread.isInterrupted());
        //中断 异常之后会擦除 中断标志
        thread.interrupt();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.println("thread is interrupted "+thread.isInterrupted());
    }
}
