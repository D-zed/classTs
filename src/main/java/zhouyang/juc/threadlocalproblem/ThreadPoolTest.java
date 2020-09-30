package zhouyang.juc.threadlocalproblem;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 在线程池中使用ThreadLocal 导致的内存泄漏demo
 * 由于 ThreadLocal的变量是 static修饰的 所以 就算其是弱引用也还是不会回收掉的
 * <p>
 * https://developer.51cto.com/art/201810/585706.htm
 * https://www.cnblogs.com/looyee/articles/10813843.html
 * <p>
 * 理解threadLocal  threadLocal其实本身不做数据的存储 自己只是作为一个key 方便当前线程找到对应的  table中的值  threadLocal 对其对象是强引用
 * currentThread 强引用 threadLocalMap 弱引用 threadLocal 强引用 value
 * 由此可见线程与threadLocalMap是强引用的关系并且生命周期相同 如果 线程不死 value永远不会回收
 * 所以threadLocal 的内存泄漏是以上原因
 * 然而为什么还要将map的key设置为弱引用呢 因为 remove等方法会请空所又为null的 所以没有手动释放和 线程存活时间过长是一个内存泄漏的直接原因 具体为什么弱引用 我甚至怀疑是为了方便我们回收
 * <p>
 * 弱引用的理解  当我们的引用的强引用没有了 只剩下弱引用了 threadLocalMap的key 的弱引用 那么势必会被回收 但是 强引用都被回收了 key也就没了啊 这种shabi操作是怎么发生的
 * 当然如果出现了这种傻逼操作 那么我们可能就无法根据key拿到值了 那么值也就是 没什么用的了  然而底层的get set remove 都有响应清除 key 为null的值的方法
 * 然而其实最终要的还是线程存活太久导致我们的线程对threadLocalMap的强引用一直存在
 *
 * @author dzd
 */
public class ThreadPoolTest {

    static class LocalVariable {
        private Long[] a = new Long[1024 * 1024];
    }

    final static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    static ThreadLocal<LocalVariable> localVariable = new ThreadLocal<LocalVariable>();

    public static void main(String[] args) throws InterruptedException {
        //由于只有五个线程所以 最多泄漏 5M
        for (int i = 0; i < 50; i++) {
            poolExecutor.execute(() -> {
                LocalVariable lv = new LocalVariable();
                localVariable.set(lv);

                System.out.println("use local variable");
                //使用vm 监控发现如果buremove 那么所有线程执行完成之后并不会释放 会导致50M的内存泄漏
                //remove 的时候（都进行gc） 3 250 240字节   非remove 24 607 560
                //  remove       不gc    7 036 456字节   非remove 66 470 880  如果不gc那么最终
                localVariable.remove();
                // lv=null;
            });
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("开始gc 虚引用被回收");

        System.out.println("gc前 ");
        //假设我们此时将 ThreadLocal 的强引用
       // System.gc();

        System.out.println("-----  over  -----------");
    }
}
