package zhouyang.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CylicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙");
        });
        //精髓集齐七颗龙珠召唤神龙
        for (int i = 1; i <= 7; i++) {
            new Thread(()->{

                try {
                    System.out.println("收集龙珠->"+Thread.currentThread().getName());
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },Integer.toString(i)).start();
        }
    }
}
