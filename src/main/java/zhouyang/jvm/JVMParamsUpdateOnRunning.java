package zhouyang.jvm;

import java.util.BitSet;
import java.util.concurrent.TimeUnit;

/**
 * 如何在运行的时候修改jvm参数
 *
 * @author dengzidi
 */
public class JVMParamsUpdateOnRunning {


    /**
     * XX参数中的 Xss 占内存的大小  等价于 ThreadStackSize 但是我们如果没有手动指定过Xss的话那么就会显示0 这个不代表没有大小而是代表使用的是默认值
     * https://www.cnblogs.com/redcreen/archive/2011/05/04/2037057.html
     * https://www.cnblogs.com/coder-programming/p/12604799.html
     * <p>
     * gc日志的解释                             gc前            gc后              gc前            gc后
     * gc的类型    新生代占用                    新生代占用         新生代总         堆内存使用情况   堆内存使用情 堆总大小      younggc耗时
     * [GC (Allocation Failure) [PSYoungGen: 3332K     ->     872K (38400K)]    3332K     ->    880K    (125952K), 0.0015461 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [Full GC (Allocation Failure) [PSYoungGen: 31357K->31339K(38400K)] [ParOldGen: 81921K->81921K(87552K)] 113279K->113261K(125952K), [Metaspace: 3209K->3209K(1056768K)], 0.0041644 secs] [Times: user=0.20 sys=0.00, real=0.00 secs]
     *
     * 默认新生代 老年代的比例 是 8:1:1=edan:s1:s0 SurvivorRatio=4 可以设置edan去和幸存区的比例
     *
     * 默认新生代和老年代的比例 =1:3
     *
     * MaxTenuringThreshold=对象进入老年代的年龄  1.8只可以[0，15] 次
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("如何在运行的时候修改jvm参数");


      //  byte[] bytes = new byte[20 * 1024 * 1024];
      /*  byte[] bytes1 = new byte[40 * 1024 * 1024];
        byte[] bytes2 = new byte[40 * 1024 * 1024];
        byte[] bytes3 = new byte[10 * 1024 * 1024];
        byte[] bytes4 = new byte[20 * 1024 * 1024];
        byte[] bytes5 = new byte[20 * 1024 * 1024];
        byte[] bytes6 = new byte[20 * 1024 * 1024];*/

        BitSet bi=new BitSet();
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}