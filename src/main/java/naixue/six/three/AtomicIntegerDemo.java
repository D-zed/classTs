package naixue.six.three;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
    public static void main(String[] args) throws InterruptedException {
        ProcessingThreadAtomic pt = new ProcessingThreadAtomic();
        Thread t1 = new Thread(pt, "t1");
        t1.start();
        Thread t2 = new Thread(pt, "t2");
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Processing count=" + pt.getCount());//8
    }
}

/**
 * Atomic 8种基本数据类型
 * AtomicReference 引用类型的原子类
 * AtomicLongArray
 */
class ProcessingThreadAtomic implements Runnable {
    private AtomicInteger count = new AtomicInteger();

    @Override
    public void run() {
        for (int i = 1; i < 5; i++) {
            processSomething(i);
            count.incrementAndGet(); //i++  //cas算法的处理来准证了原子性
        }
    }

    public int getCount() {
        return this.count.get();
    }

    private void processSomething(int i) {        // processing some job
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
