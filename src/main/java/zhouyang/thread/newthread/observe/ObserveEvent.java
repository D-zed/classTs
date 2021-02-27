package zhouyang.thread.newthread.observe;

public interface ObserveEvent<T> {

    void start();

    void running();

    void result(T result);

    void exception(T result,Exception e);
}
