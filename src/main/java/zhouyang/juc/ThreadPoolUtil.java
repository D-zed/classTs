package zhouyang.juc;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtil {

    private volatile static ThreadPoolExecutor THREAD_POOL_EXECUTOR;

    private ThreadPoolUtil(){}

    public static ThreadPoolExecutor getThreadPool(){
        if (THREAD_POOL_EXECUTOR==null){
            synchronized (ThreadPoolUtil.class){
                if (THREAD_POOL_EXECUTOR==null){
                    THREAD_POOL_EXECUTOR=new ThreadPoolExecutor(
                            Runtime.getRuntime().availableProcessors(),
                            Runtime.getRuntime().availableProcessors()+1,
                            100,
                            TimeUnit.SECONDS,
                            new LinkedBlockingDeque<>(),
                            Executors.defaultThreadFactory(),
                            new ThreadPoolExecutor.CallerRunsPolicy()
                    );
                }
            }
        }
        return THREAD_POOL_EXECUTOR;
    }

    public static void execute(Runnable runnable){
        ThreadPoolExecutor threadPool = getThreadPool();
        threadPool.execute(runnable);
    }
}
