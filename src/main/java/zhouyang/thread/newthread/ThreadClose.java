package zhouyang.thread.newthread;

import java.util.concurrent.TimeUnit;

/**
 * 线程的close方法不要用 官方建议
 *
 * 56页使用了这种方式 Java高并发编程详解书
 * @author dzd
 */
public class ThreadClose {

   static volatile boolean close=false;

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            System.out.println("I will start work");
            while (!close && !Thread.currentThread().isInterrupted()) {

            }
        });
        thread.start();
        TimeUnit.MILLISECONDS.sleep(10);
        close(thread);
    }

    static  void close(Thread thread){
        close=true;
        thread.interrupt();
    }

}
