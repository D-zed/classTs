package zhouyang.juc.consumeproduct;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 高内聚低耦合线程操作资源类
 * 判断干活唤醒通知
 * 防止虚假唤醒
 * 真传一句话，假传万卷书
 * @author dengzidi
 */
public class ProductConsumerDemoNew {


    public static void main(String[] args) {
        ConditionDemo conditionDemo=new ConditionDemo();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    conditionDemo.product();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    conditionDemo.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
    }

}

class ConditionDemo{
  private   ReentrantLock lock= new ReentrantLock();
  private Condition condition=lock.newCondition();

    int num=0;
    public void product() throws InterruptedException {
        lock.lock();
        try {
            if (num!=0){
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName()+"--"+num);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void consumer() throws InterruptedException {
        lock.lock();
        try {
            if (num==0){
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName()+"--"+num);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}