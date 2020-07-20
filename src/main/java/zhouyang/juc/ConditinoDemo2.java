package zhouyang.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 利用condition精准控制
 * 多线程之间的调用顺序 A  -  B  -  C
 * A打印5次 B十次 C十五次
 * @author ASUS
 */
public class ConditinoDemo2 {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareData.aMethod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareData.bMethod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareData.cMethod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }

}

class ShareData{
    Lock lock=new ReentrantLock();
    Condition con1=lock.newCondition();
    Condition con2=lock.newCondition();
    Condition con3=lock.newCondition();

    int currentNum=1;
    public void aMethod() throws InterruptedException {
      lock.lock();
      try {
          while (currentNum!=1){
              con1.await();
          }
          for (int i = 1; i <=5 ; i++) {
              System.out.println(Thread.currentThread().getName()+"----> "+i);
          }
          currentNum=2;
         con2.signalAll();
      }finally {
          lock.unlock();
      }
    }

    public void bMethod() throws InterruptedException {
        lock.lock();
        try {

            while (currentNum!=2){
                con2.await();
            }
            for (int i = 1; i <=10 ; i++) {
                System.out.println(Thread.currentThread().getName()+"----> "+i);
            }
            currentNum=3;
            con3.signalAll();

        }finally {
            lock.unlock();
        }
    }

    public void cMethod() throws InterruptedException {
        lock.lock();
        try {
            while (currentNum!=3){
                con3.await();
            }
            for (int i = 1; i <=15 ; i++) {
                System.out.println(Thread.currentThread().getName()+"----> "+i);
            }
            currentNum=1;
            con1.signalAll();

        }finally {
            lock.unlock();
        }
    }
}
