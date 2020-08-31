package zhouyang.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 当线程的交替执行的时候与非交替执行的时候 reentrantlock的时候不一样
 *  交替执行的时候仅仅对state字段加了个cas操作 性能几乎可以忽略 ，这个比没有优化的sync相比的优势
 *
 *  tryAcquire 尝试第一次获取锁， 如果交替执行的情况就直接获取就ok了
 *
 *  如果没获取到 则需要 执行 addWaiter 方法 入队 并且返回当前的节点
 * @author ASUS
 */
public class ReentrantLockDemo {

    public static void main(String[] args)  {
        //这个锁叫做可重入锁，也就是说线程可以进入任意一个它已经获取锁的同步代码块，
        //外层代码获取锁之后，内层代码仍然能够获取该锁的代码，同一个线程在外层
        //方法获取到了锁在内层方法可以自动获取到锁，也就是可重入锁
        //类比你进入你家大门也就代表你进入了你家了

        //自己理解就是获取外层锁的线程默认也是已经获取了内层锁
        //ReentrantLock 和 syncronized都是可重入锁
        ReentrantLock reentrantLock=new ReentrantLock();
        //这个被打断了不会抛出interrupt异常

        try {
            reentrantLock.lock();
        }catch (Exception e){

        }finally {
            reentrantLock.unlock();
        }
        //这个被打断了就抛出 interrup异常 且在抢占之前判断是否被中断

        try {
            reentrantLock.lockInterruptibly();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }
}
