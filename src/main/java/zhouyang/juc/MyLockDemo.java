package zhouyang.juc;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 利用cas自己手动实现一个自旋锁
 * 自旋锁的优点是线程不会阻塞减少了线程上下文切换造成的性能浪费，但是如果太频繁会造成cpu的负担
 * @author dengzidi
 */
public class MyLockDemo {

    public static void main(String[] args) {

        MyLock myLock = new MyLock();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    myLock.add();
                    myLock.addSync();
                }
            },Integer.toString(i)).start();
        }

        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println("不加锁的情况下--"+myLock.count);
        System.out.println("加锁的情况下--"+myLock.countSnc);



    }
}

class MyLock{

    int count=0;

    int countSnc=0;
    AtomicReference<Thread> atomicReference=new AtomicReference<>();
    public void getLock(){
        Thread thread = Thread.currentThread();
        //抢占锁不成功则继续抢  继续自旋
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void unLock(){
        //解锁的时候也是只能解锁自己
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
       // 其实直接这个也可以因为不会有两个线程同时获取锁的 atomicReference.set(null);
    }

    public void add(){
     count++;
    }

    public void addSync(){
        getLock();
        try {
            countSnc++;
        }finally {
            unLock();
        }

    }
}