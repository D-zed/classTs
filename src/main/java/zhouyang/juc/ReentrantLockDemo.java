package zhouyang.juc;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) {
        //这个锁叫做可重入锁，也就是说线程可以进入任意一个它已经获取锁的同步代码块，
        //外层代码获取锁之后，内层代码仍然能够获取该锁的代码，同一个线程在外层
        //方法获取到了锁在内层方法可以自动获取到锁，也就是可重入锁
        //类比你进入你家大门也就代表你进入了你家了

        //自己理解就是获取外层锁的线程默认也是已经获取了内层锁
        //ReentrantLock 和 syncronized都是可重入锁
        ReentrantLock reentrantLock=new ReentrantLock();
        reentrantLock.lock();
        try {

        }catch (Exception e){

        }finally {
            reentrantLock.unlock();
        }
    }
}
