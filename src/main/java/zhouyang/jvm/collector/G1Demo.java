package zhouyang.jvm.collector;

import java.util.Random;

/**
 * G1收集器，一款偏向于服务端的垃圾收集器，高吞吐量 并且暂停时间更短  既要马儿跑 又不给马儿吃草
 * 用户可以指定期望的垃圾收集时间(这个非常重要，因为别的没有这个参数)
 * g1横跨 老年代 年轻代  也可以说在g1在宏观上不存在老年代和年轻代，并且避免全内存的大范围操作 可以使得更快
 *
 *  其中引入了region 分区 对每个分区进行gc 小范围内进行新生区年老区的区分 g1只有逻辑上的分代概念（不在物理上整体区分为edan s1 s0）
 *
 * 分而治之 精准控制
 *
 *  Humongous 大对象区
 *
 *  最大的好处就是化整为零 避免大面积的gc                                            最大的gc时间100毫秒
 *  -Xms5m -Xmx5m -XX:+UseG1GC -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:MaxGCPauseMillis=100
 *
 *  G1与 CMS的优势  G1没有内存碎片  且可配置gc停顿最大时间（jvm可尽可能的小于这个时间）
 *  初始标记 : 标记所有的gcroots
 *  并发标记: 标记GcRoot trace 的链路
 *  最终标记: 修正并发标记期间因程序运行而导致变化的那一部分对象
 *   筛选回收: 根据时间进行价值最大化的回收
 *
 *
 * @author dengzidi
 */
public class G1Demo {
    public static void main(String[] args) {
        //[Full GC (Allocation Failure) [CMS: 3108K->3108K(4800K), 0.0017272 secs] 3108K->3108K(6016K), [Metaspace: 3737K->3737K(1056768K)], 0.0017485 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        String string="aaa";
        while (true){
            string += string+new Random().nextInt(3333333)+new Random().nextInt(55555555);
            // System.out.println(string);
            //string.intern();
        }
    }
}
