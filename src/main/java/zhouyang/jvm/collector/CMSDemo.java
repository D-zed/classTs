package zhouyang.jvm.collector;

import java.util.Random;

/**
 * 并发标记清除
 * 这个是个只能老年代用的收集器
 * 会产生内存碎片，非常适合用在互联网的B/S系统上 适合用在堆内存大的
 * 在标记过程中微微暂停，然后并发收集与用户线程共存
 * 开启了CMS 则自动激活ParNew 而且如果cms除了问题则会自动切换为 (要在堆内存耗尽之前完成cms，如果出现了偏差则切换为serialold兜底)serialold
 *
 * 默认情况下cms不会堆元空间清理需要开启
 *
 * 且其中包括了 初始标记（仅仅标记一下gcroots能直接关联的对象） 微停 并发标记停顿 重新标记 微停 并发清除
 * https://blog.csdn.net/LuoZheng4698729/article/details/107532325/
 *  -Xms5m -Xmx5m -XX:+UseConcMarkSweepGC -XX:+PrintCommandLineFlags -XX:+PrintGCDetails
 *  触发gc 的阈值                            标识仅根据配置的 阈值参数来触发gc
 *  -XX:CMSInitiatingOccupancyFraction   -XX:+UseCMSInitiatingOccupancyOnly
 *
 *  -XX:+UseCMSCompactAtFullCollection (开启整理，会导致停顿时间边长，因为整理的过程中不会并发)  -XX:CMSFullGCsBeforeCompaction=3  (多少次不整理的cms后进行一次整理 默认是0代表每次都整理)
 * @author dengzidi
 */
public class CMSDemo {
    public static void main(String[] args) {
        int length = " 为投保人拟订投保方案、选择保险人以及办理投保手续,  协助被保险人或者受益人进行索赔,  再保险经纪业务,  为委托人提供防灾、防损或者风险评估、风险管理咨询服务,  中国银保监会批准的其他业务".length();
        System.out.println(length);
        //[Full GC (Allocation Failure) [CMS: 3108K->3108K(4800K), 0.0017272 secs] 3108K->3108K(6016K), [Metaspace: 3737K->3737K(1056768K)], 0.0017485 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        String string="aaa";
        while (true){
            string = string+new Random().nextInt(3333333)+new Random().nextInt(55555555);
            // System.out.println(string);
            //string.intern();
        }
    }
}
