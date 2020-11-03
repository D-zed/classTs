package zhouyang.juc.thread.newthread.observable;

/**
 * 测试synchronized关键字不会被 重写
 * @author dzd
 */
public class EmptyLifecycleImpl extends EmptyLifecycle{
    @Override
    public  void onStart(Thread thread) {
        super.onStart(thread);
    }
}
