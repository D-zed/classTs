package zhouyang.juc.thread;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程
 * @author dzd
 */
public class DeamonDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {

                while (true){}

        });

        //设置其为守护线程  这样主线程一死就死了
        thread.setDaemon(true);

        thread.start();

    }
}
