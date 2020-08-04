package zhouyang.jvm;

/**
 * 垃圾收集方式 ，和垃圾收集器
 * 穿行    并行      并发
 * Serial  parallel  cms  g1
 *
 * @author dzd
 */
public class GarbageDemo {

    /**
     * java -XX:PrintCommandLineFlags -version 查看默认的垃圾收集器
     * serial 串行收集器 gc的时候会stop the world      一个人干活既扫地又擦玻璃
     * parallel 多个gc线程 然而也会造成 stop the world 多核服务器上性能优于serial  多个人一起擦玻璃扫地
     * cms 不会stop the world 但是整个系统的运行性能会被gc线程抢占   concurrent mark  并发标记清楚法  一般工作中使用的是这个
     * g1 历经十年在1.8开始  1.9使用的就是G1
     * @param args args
     */
    public static void main(String[] args) {

    }
}
