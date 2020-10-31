package zhouyang.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产消费者模式一个变种 用condition控制线程通信 指定唤醒
 *  多线程之间的调用顺序 A  -  B  -  C
 *  A打印5次 B十次 C十五次
 *  精准控制
 * @author dengzidi
 */
public class ProductConsumerConditionDemo extends Thread{


    public static void main(String[] args) {
        ShareData1 shareData1 = new ShareData1();
        new Thread(()->{
            shareData1.a();
        }).start();
        new Thread(()->{
            shareData1.b();
        }).start();
        new Thread(()->{
            shareData1.c();
        }).start();
    }
}

class ShareData1{
    ReentrantLock lock=new ReentrantLock();
    Condition con1=lock.newCondition();
    Condition con2=lock.newCondition();
    Condition con3=lock.newCondition();

    int sort=1;
    public void a(){
        lock.lock();
        try {
            if (sort!=1){
                con1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("a");
            }
            sort=2;
            con2.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void b(){
        lock.lock();
        try {
            if (sort!=2){
                con2.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("b");
            }
            sort=3;
            con3.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void c(){
        lock.lock();
        try {
            if (sort!=3){
                con3.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("c");
            }
            sort=1;
            con1.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}


