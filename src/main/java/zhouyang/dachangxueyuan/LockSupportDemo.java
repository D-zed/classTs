package zhouyang.dachangxueyuan;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lockSupport不需要锁可以单独使用并且可以直接指定线程进行阻塞释放
 * 每一个线程都有一个permit许可证 相当于01开关 默认是0
 * unpark是发放通行证 park是获得通行证 所以首先park无法获取通行证就会阻塞了
 * 但是如果多次unpark仍然会是一个通行证
 * park调用的是unsafe类的native park方法
 *
 * @author dzd
 */
public class LockSupportDemo {


    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {

            }
            System.out.println(Thread.currentThread().getName() + "\t" + "come in ...");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t" + "---被唤醒");
        }, "a");
        a.start();


        Thread b = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "come in ...");
            //unPark可以再park之前运行 并且这样后边的park就会失效了
            LockSupport.unpark(a);
            System.out.println(Thread.currentThread().getName() + "\t" + "---被唤醒");
        }, "b");
        b.start();
    }

}
