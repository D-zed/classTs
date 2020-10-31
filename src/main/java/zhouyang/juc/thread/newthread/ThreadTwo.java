package zhouyang.juc.thread.newthread;

import java.util.concurrent.TimeUnit;

public class ThreadTwo extends Thread{

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
            ThreadOne threadOne = new ThreadOne();
            threadOne.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("heh2");
    }
}
