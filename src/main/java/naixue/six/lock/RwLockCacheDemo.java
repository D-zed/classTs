package naixue.six.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RwLockCacheDemo {
    //读写锁
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    //读锁
    ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    //写锁
    ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
    //缓存数据
    Map<String, Object> data = new HashMap<>();
    static final ThreadPoolExecutor executor =
            new ThreadPoolExecutor(8, 12, 10,
                                    TimeUnit.SECONDS,
                                    new ArrayBlockingQueue<>(30),
                                    new ThreadPoolExecutor.CallerRunsPolicy());//如果线程池没有关闭直接运行任务(性能不高)


    //-从缓存中获取数据，如果不存在及从数据库或者其他地方获取数据，当前为模拟自动生成数据
    public Object cache(String key) {
        try {
            //加读锁-读锁属于共享锁可以支持多个线程获取锁，但此时排斥写锁
            readLock.lock();
            //如果有对应的key
            if (Objects.isNull(data.get(key))) {
                //解除读锁
                readLock.unlock();
                //添加写锁，保证写锁内操作只有一个线程能执行-避免该处过多线程操作导致崩溃或数据不一致
                writeLock.lock();
                try {
                    //双重判断，防止有其他线程已经处理，出现重复处理的情况
                    if (Objects.isNull(data.get(key))) {
                        //获取数据 TODO 需要进行的具体操作
                        String s = UUID.randomUUID().toString();
                        System.out.println("key： " + key + " value: " + s);
                        data.put(key, s);
                    }
                    //在没释放写锁前，加读锁，将其他线程的可能进行的写锁屏蔽-降级写锁处理，保证数据的一致性
                    readLock.lock();
                } finally {
                    //最终释放写锁-此时仍然存在读锁
                    writeLock.unlock();
                }
            }
            return data.get(key);
        } finally {
            //最终释放读锁
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        RwLockCacheDemo cacheData = new RwLockCacheDemo();
        for (int i = 0; i < 1000; i++) {
            Double random = Math.random() * 10;
            int key = random.intValue();
            executor.submit(() -> System.out.println(cacheData.cache(key + "")));
        }
        executor.shutdown();
    }
}
