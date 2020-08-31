package zhouyang.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题的解决
 * @author dengzidi
 */
public class ABASolution {

static    AtomicReference<Integer> atomicReference= new AtomicReference(100);
static AtomicStampedReference<Integer> atomicStampedReference= new AtomicStampedReference(100,0);

    public static void main(String[] args) {
        //首先模拟一下ABA问题的产生
/*        new Thread(()->{
            //次数如果大于128则是一个新的Integer对象所以不行
            System.out.println(Thread.currentThread().getName()+"---"+atomicReference.compareAndSet(100,101)+"--"+atomicReference.get());
            System.out.println(Thread.currentThread().getName()+"---"+atomicReference.compareAndSet(101,100)+"--"+atomicReference.get());

        },"AAA").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"--"+atomicReference.compareAndSet(100,2020)+"--"+atomicReference.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"bbbb").start();*/


        //首先模拟一下ABA问题的产生
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //次数如果大于128则是一个新的Integer对象所以不行
            System.out.println(Thread.currentThread().getName()+"---哈哈version"+atomicStampedReference.getStamp()+"---"+atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1)+"--"+atomicReference.get());

            System.out.println(Thread.currentThread().getName()+"---version"+atomicStampedReference.getStamp()+"---"+atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1)+"--"+atomicReference.get());

        },"CCCC").start();

        new Thread(()->{
            try {
                int stamp = atomicStampedReference.getStamp();
                System.out.println("init stamp ---"+stamp);
                TimeUnit.SECONDS.sleep(3);
                //因为走到这的时候版本号已经变成了2了  aba问题使用了版本号解决
                System.out.println(Thread.currentThread().getName()+atomicStampedReference.getStamp()+"--"+atomicStampedReference.compareAndSet(100,2020,stamp,atomicStampedReference.getStamp()+1)+"--"+atomicReference.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"DDDD").start();


    }
}
