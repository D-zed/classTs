package zhouyang.jvm.collector;

import java.util.Random;

/**
 * G1收集器，一款偏向于服务端的垃圾收集器，高吞吐量 并且暂停时间更短  既要马儿跑 又不给马儿吃草
 * 用户可以指定期望的垃圾收集时间
 * g1横跨 老年代 年轻代
 *
 *  -Xms5m -Xmx5m -XX:+UseG1GC -XX:+PrintCommandLineFlags -XX:+PrintGCDetails
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
