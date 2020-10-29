package zhouyang.dachangxueyuan;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * aqs是一个基石框架 ，高级的抽象对并发同步做了最基本的规范
 *
 * @author dzd
 */
public class AqsDemo {

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        //
        lock.lock();
        try {

        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }
}
