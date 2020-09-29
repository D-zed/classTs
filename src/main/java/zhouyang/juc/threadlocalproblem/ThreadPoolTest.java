package zhouyang.juc.threadlocalproblem;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 在线程池中使用ThreadLocal 导致的内存泄漏demo
 * @author dzd
 */
public class ThreadPoolTest {

    static class LocalVariable{
        private Long[] a=new Long[1024*1024];
    }

    final static ThreadPoolExecutor poolExecutor=new ThreadPoolExecutor(5,5,1, TimeUnit.SECONDS,new LinkedBlockingQueue<>());

    final static  ThreadLocal<LocalVariable> localVariable=new ThreadLocal<LocalVariable>();

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 50; i++) {
            poolExecutor.execute(()->{
                localVariable.set(new LocalVariable());

                System.out.println("use local variable");
            });
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println("-----  over  -----------");
    }
}
