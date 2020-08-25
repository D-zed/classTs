package zhouyang.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁的功能
 *
 * @author dengzidi
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {

//一句话总结读写锁的精髓  读与读不互斥  读和写互斥 写和写互斥

        MyCache myCache = new MyCache();
//此个案例中如果没有读写锁那么读和写之间是交替进行

        //读的时候可以读但是不可以写  写的时候独占

       // myCache.get(Integer.toString(1));
        for (int i = 1; i <= 10; i++) {
            final int v = i;
            new Thread(() -> {
                myCache.put(Integer.toString(v), Integer.toString(v));
            }, "A" + i).start();
        }
        for (int i = 1; i <= 10; i++) {
            final int v = i;
            new Thread(() -> {
                myCache.get(Integer.toString(v));
            }, "B" + i).start();
        }


    }
}

//高内聚  就是把功能内聚到一个模块提高复用性，减少与调用者的关联
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public void put(String key, Object value) {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t写入数据 key：" + key);

            //TimeUnit.SECONDS.sleep(3);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }

    }

    public void get(String key) {
        /**
         * 这个是获取读锁的时候的一个判断，读写锁其中由两个状态来决定是否可以获取到锁，
         * sharedCount       共享锁的持有者数量
         * exclusiveCount    独占锁的持有者数量
         * 独占锁的持有者数量如果不为零并且持有者也不是当前线程 则直接获取锁失败
         *   if (exclusiveCount(c) != 0 &&
         *                 getExclusiveOwnerThread() != current)
         *                 return -1;
         *   如果获取失败了则进入共享锁的等待队列
         */
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t读取数据");
           // TimeUnit.SECONDS.sleep(3);
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t读取完成 value:" + value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }

    }
}