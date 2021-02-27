package zhouyang.thread.newthread.observable;

/**
 *
 * @author dzd
 */
public interface Observable {

    enum Cycle
    {
        STARTED,RUNNING,DONE,ERROR
    }

    /**
     * 获取 当前人物的生命周期状态
     * @return
     */
    Cycle getCycle();

    /**
     * 启动线程方法
     */
    void start();

    /**
     * 线程的打断方法
     */
    void interrupt();

}
