package zhouyang.jvm.collector;

import java.util.Random;

/**
 * 并发标记清除
 * 这个是个只能老年代用的收集器
 * 会产生内存碎片，非常适合用在互联网的B/S系统上 适合用在堆内存大的
 * 在标记过程中微微暂停，然后并发收集与用户线程共存
 * 开启了CMS 则自动激活ParNew 而且如果cms除了问题则会自动切换为 (要在堆内存耗尽之前完成cms，如果出现了偏差则切换为serialold兜底)serialold
 *
 *  -Xms5m -Xmx5m -XX:+UseConcMarkSweepGC -XX:+PrintCommandLineFlags -XX:+PrintGCDetails
 *
 * @author dengzidi
 */
public class CMSDemo {
    public static void main(String[] args) {
        //[Full GC (Allocation Failure) [CMS: 3108K->3108K(4800K), 0.0017272 secs] 3108K->3108K(6016K), [Metaspace: 3737K->3737K(1056768K)], 0.0017485 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        String string="aaa";
        while (true){
            string = string+new Random().nextInt(3333333)+new Random().nextInt(55555555);
            // System.out.println(string);
            //string.intern();
        }
    }
}
