package zhouyang.juc.mylock;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;

/**
 * @author dzd
 */
public class ProductConsumerMyLock {

    final static NoReentrantLock noReentrantLock = new NoReentrantLock();
    final static Condition notFull = noReentrantLock.newCondition();
    final static Condition notEmpty = noReentrantLock.newCondition();
    final static Queue<String> queue = new LinkedBlockingQueue<String>();
    final static int queueSize = 10;

    public static void main(String[] args) {

        //创建生产线程
        new Thread(()->{
            noReentrantLock.lock();
            try {
                while (queue.size()==queueSize){
                        notEmpty.await();
                }
                queue.offer("ele");
                notFull.signalAll();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                noReentrantLock.unlock();
            }
        }).start();

        new Thread(()->{
            noReentrantLock.lock();
            try {
              while (queue.size()==0){
                  notFull.await();
              }
                String poll = queue.poll();
                System.out.println("消费线程 "+poll);
                notEmpty.signalAll();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                noReentrantLock.unlock();
            }
        }).start();


    }
}
