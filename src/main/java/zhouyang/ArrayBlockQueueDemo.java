package zhouyang;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 此锁的内部是使用了数组 并且锁的粒度很大 相当了一个全局锁
 * 所以他的size也是准确的 但是性能也较弱
 * @author dzd
 */
public class ArrayBlockQueueDemo {
    public static void main(String[] args) {
        ArrayBlockingQueue arrayBlockingQueue=new ArrayBlockingQueue(6);


        arrayBlockingQueue.offer("ddd");
        arrayBlockingQueue.size();

    }
}
