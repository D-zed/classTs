package zhouyang.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    public static void main(String[] args) {
        //信号量法  例子争车位 车位的数量是固定的 十多个  车子也是多，也就是多对多
        //例子7个车子抢四个车位
        //信号量两个作用一个是 并发线程数量的控制 多个线程的互斥 比如当信号量为1的时候则 所有线程是互斥关系

        //初始化车位
        Semaphore semaphore = new Semaphore(3);

        //来了七辆车抢车位，并且抢到之后 停三秒钟
        for (int i = 1; i <= 7; i++) {

            new Thread(()->{
                try {
                    //车位的抢占，我们拿到了车位则车位的总数就少了一个 没有抢到的只能等待
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"——>车抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"-->车开走了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放当前持有的信号量唤醒  也就是通知等待的汽车
                    semaphore.release();
                }
            },Integer.toString(i)).start();
        }
    }
}
