package zhouyang.thread.newthread;

/**
 * 看过源码之后可以理解到 真正代表线程的就是Thread类 开启一个线程也就只有一种方法 new Thread();
 *
 * 1. 每一个线程都是在另一个线程中创建的并且默认这个线程就是对应线程的父线程
 * 2. 默认情况下 子线程会被加入到父线程的线程组 ThreadGroup()
 * 3.默认和父线程有同样的优先级
 * 4.默认和父线程有同样的 daemon 所以当我们 要设置一个线程是守护线程就要设置 setDaemon=true
 * 5.线程的优先级不可以大于线程组的优先级
 * @author dzd
 */
public class ThreadMain {
    public static void main(String[] args) {
        ThreadOne threadOne = new ThreadOne();
        ThreadTwo threadTwo = new ThreadTwo();


        threadOne.start();
        threadTwo.start();

        System.out.println("父线程的线程组:"+Thread.currentThread().getThreadGroup().getName());
        System.out.println("threadOne的线程组:"+threadOne.getThreadGroup().getName());
        System.out.println("threadTwo的线程组:"+threadTwo.getThreadGroup().getName());

    }
}