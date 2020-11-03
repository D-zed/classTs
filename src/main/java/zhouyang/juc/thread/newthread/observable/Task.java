package zhouyang.juc.thread.newthread.observable;

public interface Task<T> {

    /**
     * 任务执行接口
     * @return
     */
    T call();
}
