package zhouyang.juc.thread.newthread.volatile1;

import java.util.concurrent.TimeUnit;

/**
 * 演示了 volatile的内存可见性  防止指令重排
 * 184页
 *
 * volatile 有几个作用
 * 使用了内存屏障实现，防止指令重排
 * 读的时候强制刷主内存
 * 写操作会导致其他线程工作内存的缓存数据失效
 *
 *         synchronized 和 volatile对比
 *  原子性：    yes           no
 *  内存可见性   yes（jvm指令 monitor enter monitor exit）           yes（）
 *  指令重排     yes（虽然是块代码的强制同步保证顺序，但是块中的代码无法保证顺序）           no
 *  阻塞        yes             no
 * @author dzd
 */
public class VolatileFoo {

    final static int MAX = 5;
    static  int init_value = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = init_value;
            while (localValue < MAX) {
                if (init_value != localValue) {
                    if (init_value != localValue) {
                        System.out.println("被修改了 "+init_value);
                        localValue = init_value;
                    }
                }
            }
        }, "reader").start();

        new Thread(() -> {
            int localValue = init_value;
            while (localValue < MAX) {
                ++localValue;
                System.out.println("修改 "+localValue);
                init_value=localValue;
                try {
                    TimeUnit.SECONDS.sleep(2);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }, "updater").start();

    }

}