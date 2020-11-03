package zhouyang.juc.thread.newthread.observable;

/**
 * @author dzd
 */
public interface TaskLifecycle<T> {

    //任务启动是触发onStart
    void  onStart(Thread thread);

    void onRunning(Thread thread);

    void onFinish(Thread thread,T result);

    void onError(Thread thread,Exception e);

}
