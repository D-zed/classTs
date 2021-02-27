package zhouyang.thread;

import java.util.concurrent.TimeUnit;

/**
 * 资源互相等待就造成了死锁
 * 只要让两个线程的资源申请顺序达到一直即可解决死锁问题
 * @author dzd
 */
public class DeadLockDemo {

    private static Object resourceA=new Object();
    private static Object resourceB=new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (resourceA){
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resourceB){

                }
            }
        }).start();

        new Thread(()->{
            synchronized (resourceB){

                synchronized (resourceA){

                }
            }
        }).start();
    }
}
