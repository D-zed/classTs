package zhouyang.jvm.jvmparam;

import java.util.concurrent.TimeUnit;

public class JVMParams {
    /**
     * 官方文档看参数
     * https://docs.oracle.com/javase/8/docs/technotes/tools/windows/java.html
     *
     * jvm 参数分为了 三种 其中我们进行调试的是XX类型的参数 ，而且Xms Xmx也属于XX类型的参数只不过是 初始堆内存 最大堆内存的缩写
     * 等价于以下两个  并且默认的最大堆内存是本机堆内存的1/4 最小的堆内存是1/64
     * -XX:InitialHeapSize=260046848  -XX:MaxHeapSize=4139778048
     * 且 XX类型的参数分为了两种设置方式一种是 KV类型的还有一种是 bool类型的（开启关闭）
     * Non-default 后边的参数代表的是初始化加载系统设置的jvm参数
     * command line 后边的是 我们的命令行参数
     * @param args args
     * @throws InterruptedException InterruptedException
     *
     *
     * java -XX:+PrintFlagsInitial 拆看jvm安装之后的初始参数
     * java -XX:+PrintFlagsFinal -version  查看最终的jvm参数 也即我们自己修改过的值
     *
     * := true  这种显示成 :=的就是jvm启动的时候加载配置文件或者人为修改的  没有:则是没改过的
     *
     * java -XX:+PrintCommandLineFlags -version 方便查看当前环境使用什么垃圾回收器
     *
     * 查看当前运行的程序的栈内存默认大小 发线是0 0代表没改过而已不是真的是0
     * jinfo -flag ThreadStackSize 114460   等价于  Xss 单个线程栈的大小一般位 512k 1024k
     *
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("jvm 参数是什么");

        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}