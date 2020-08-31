package zhouyang.juc;

import java.util.concurrent.locks.StampedLock;

/**
 * java 1.8新增的锁
 * 且这是个不可重入锁
 *
 * 该锁提供了三种模式的读写控制，当调用获取锁方法时候，会返回一个long类型
 * 的变量，我们称之为戳，获取锁失败获取的戳是0，调用释放锁和转换锁的方法时
 * 需要传入获取锁时返回的stamp值
 * stampedLock提供的三种读写模式的锁分别如下
 * 写锁 writeLock 独占锁 此时只有一个线程可以获取该锁，当一个线程获取该锁
 * 其他请求读锁和写锁的线程必须等待，
 *
 * readLock 悲观读锁
 * tryOptimisticRead 乐观读锁 所谓乐观和悲观，乐观就是总是认为能拿到锁
 * 然后在使用前做校验，悲观是总是认为拿不到
 * 适合读多写少的情况
 *
 * @author dzd
 */
public class StampLockDemo {

    private double x,y;

    //锁实例
    private final StampedLock s1=new StampedLock();

    //写锁
    void move(double deltax,double deltay){
        long stamp = s1.writeLock();
        try {
            x+=deltax;
            y+=deltay;
        }finally {
            s1.unlockWrite(stamp);
        }
    }

    //乐观读锁
    double distanceFromOrigin(){

        //获取乐观读锁
        long stamp = s1.tryOptimisticRead();
        double currentX=x,currentY=y;
        //检查在 1 处获取了读锁戳记后，锁没有被其他线程排他性抢占
        if (!s1.validate(stamp)){
            //如果被抢占了获取一个共享读锁
            stamp = s1.readLock();
            try {
                currentX=x;
                currentY=y;
            }finally {
                s1.unlockRead(stamp);
            }
        }

        return Math.sqrt(currentX*currentX+currentY*currentY);
    }


    void moveIfAtOrigin(double newX,double newy){
        long stamp = s1.readLock();
        try {

            while (x==0.0 && y==0.0){
                //尝试将读锁变写锁
                long ws = s1.tryConvertToWriteLock(stamp);
                if (ws!=0L){
                    stamp=ws;
                    x=newX;
                    y=newy;
                }else {
                    //锁升级失败的情况 先释放 再手动获取写锁
                    s1.unlockRead(stamp);
                    stamp=s1.writeLock();
                }
            }
        }finally {
            s1.unlock(stamp);
        }


    }
}
