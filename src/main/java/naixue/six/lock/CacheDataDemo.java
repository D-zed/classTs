package naixue.six.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 1、ReentrantReadWriteLock在1.5以后出现，为了解决读多写少的业务场景的
 * 2、读锁readLock共享锁 写锁writeLock独享锁
 * 3、读锁和读说之间不互斥、只要有一把锁是写锁那么就互斥
 * ？？AQS？？
 *
 */
public class CacheDataDemo {

    static Map<String,Object> cacheMap = new HashMap();
    static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    static Lock readLock = rwLock.readLock();
    static Lock writeLock = rwLock.writeLock();

    /**
     * 缓存的更新和修改
     * @param key
     * @return
     */
    public static final  Object get(String key){
        readLock.lock();//读锁
        try {
            return cacheMap.get(key);
        }finally {
            readLock.unlock();
        }
    }

    public static final Object set(String key,Object value){
        writeLock.lock();
        try {
            return cacheMap.put(key,value);
        }finally {
            writeLock.unlock();
        }
    }

}
