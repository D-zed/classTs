package naixue.six.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1、synchronized的锁代码块上或者方法上、lock可以放在任何位置
 * 2、synchronized 发生异常可以自定释放锁，但是lock不会自动释放锁，一定在finally手动释放
 * 3、 boolean b = lock.tryLock();得到获取锁的结果。synchronized不行
 */
public class ReentrantLockDemo implements Runnable{
    private  int count=0;
    private Lock lock=new ReentrantLock();
    public void inc(){
        lock.lock(); //加锁
        try {
            Thread.sleep(1);
            count++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            // 释放锁
            lock.unlock();
        }
    }

    @Override
    public void run() {
        inc();
    }

    public static void main(String[] args) {
        ReentrantLockDemo rl = new ReentrantLockDemo();
        new Thread(rl).start();
        new Thread(rl).start();
    }
}
