package zhouyang.juc.thread.newthread;

import java.util.concurrent.TimeUnit;

public class ThreadOne extends Thread{

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("heh");
    }
}
