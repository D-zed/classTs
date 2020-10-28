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
 *  深入理解jvm pdf 148页
 *  虽然G1也仍然遵循分代收集理论设计，但是不再坚持固定大小以及固定数量的分代区域划分，而是把连续的java堆划分为多个大小相等的独立区域region
 *  每一个region都可以根据需要扮演新生代的eden空间 survivor空间 或者劳您阿呆空间
 *  并且region中还有一类特殊的humongous区域 专门用来存储大对象，对于那些超级大的对象将会被存放在N个连续的Humongous region中G1大部分情况会将其当作老年代处理，G1认为只要大小超过了一个region容量一半的对象即
 *  认为是大对象 且每个region的大小可以通过参数设置 -XX:G1HeapRegionSize设定（取值未 1MB-32M 必须是2的整数幂）
 *  总之G1总是可以优先处理收益最大的region 细粒度的回收  并且可以指定期望的收集停顿时间
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
