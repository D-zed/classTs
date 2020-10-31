package zhouyang.juc.thread.newthread;

import java.util.stream.IntStream;

/**
 * yield是将 线程从 Running状态切换到 Runnable状态
 * 然而会立刻进行下一次抢占没毛用
 *
 * @author ASUS
 */
public class ThreadYield {

    public static void main(String[] args) {
        IntStream.range(0, 2).mapToObj(ThreadYield::create).forEach(Thread::start);


    }

    private static Thread create(long index) {
        return new Thread(() -> {

            //
            if (index == 1) {
                Thread.yield();
            }
            System.out.println(index);
        });

    }
}
