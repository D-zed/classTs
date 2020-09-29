package zhouyang.threadpoolnamefactory;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.Locale;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池实际使用中的设置
 * @author dzd
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        //DefaultThreadFactory
        //线程池中的线程的名字是  namePrefix + threadNumber.
        //getAndlncrement（） 所以 我们只要对namePrefix进行名称的设置即可

        ThreadPoolExecutor executorOne =
                new ThreadPoolExecutor
                        (5, 5, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>()
                        ,new DefaultThreadFactory("吃了么"));
        ThreadPoolExecutor executorTwo =
                new ThreadPoolExecutor
                (5, 5, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>()
                ,new DefaultThreadFactory("吃饱了"));

        executorOne.execute(()->{
            Integer a=null;
            String s = a.toString();

        });

        executorTwo.execute(()->{
            Integer a=null;
            String s = a.toString();

        });

    }

}
