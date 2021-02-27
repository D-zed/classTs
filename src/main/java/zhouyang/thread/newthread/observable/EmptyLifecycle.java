package zhouyang.thread.newthread.observable;

/**
 * Adapter
 * @author dzd
 */
public class EmptyLifecycle<T> implements TaskLifecycle<T>{
    @Override
    public synchronized void onStart(Thread thread) {

    }

    @Override
    public void onRunning(Thread thread) {

    }

    @Override
    public void onFinish(Thread thread, T result) {

    }

    @Override
    public void onError(Thread thread, Exception e) {

    }
}
