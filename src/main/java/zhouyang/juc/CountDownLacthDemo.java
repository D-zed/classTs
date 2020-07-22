package zhouyang.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 计数器的使用
 * @author dengzidi
 */
public class CountDownLacthDemo {

    public static void main(String[] args) throws InterruptedException {
        //计数器初始化
        CountDownLatch countDownLatch=new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                    System.out.println("我是"+Thread.currentThread().getName()+"\t 离开教室");
                    countDownLatch.countDown();
            },Integer.toString(i)).start();
        }
        //阻塞检查计数器的值是否为0
        countDownLatch.await();
        System.out.println("班长关门走人");
    }

}
