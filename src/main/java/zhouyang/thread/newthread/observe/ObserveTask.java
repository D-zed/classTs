package zhouyang.thread.newthread.observe;

/**
 * 真正执行的任务
 * @author ASUS
 */
public interface ObserveTask<T> {

    T call ();

}
