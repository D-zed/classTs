package zhouyang.juc;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列之一同步队列
 * @author dengzidi
 */
public class SyncQueueDemo {

    //这个队列如果put值的时候 队列不存储元素  产生一个消费一个 否则阻塞
    public static void main(String[] args) {

        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();


        new Thread(()->{
            try {
                System.out.println("put aaaa");
                synchronousQueue.put("aaaa");
                System.out.println("put bbbb");
                synchronousQueue.put("bbbb");
                System.out.println("put cccc");
                synchronousQueue.put("cccc");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"a").start();


        //b线程每5秒消费一个
        new Thread(()->{
            try {
                for (int i = 0; i < 3; i++) {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName()+"---消费");
                   synchronousQueue.take();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"b").start();
    }
}
