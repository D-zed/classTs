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

        for (int i = 1; i <= 10; i++) {
            final int v = i;
            new Thread(() -> {
                myCache.get(Integer.toString(v));
            }, "B" + i).start();
        }

        for (int i = 1; i <= 10; i++) {
            final int v = i;
            new Thread(() -> {
                myCache.put(Integer.toString(v), Integer.toString(v));
            }, "A" + i).start();
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

            TimeUnit.SECONDS.sleep(3);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t写入完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }

    }

    public void get(String key) {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t读取数据");
            TimeUnit.SECONDS.sleep(3);
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t读取完成 value:" + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }

    }
}