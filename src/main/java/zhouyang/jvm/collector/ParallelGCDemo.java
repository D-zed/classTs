package zhouyang.jvm.collector;

import java.util.Random;

/**
 * 新生代老年代都是并行
 * 并且这个比较
 * jvm的七大垃圾收集器中的
 * 吞吐量 =运行代码的时间/总时间
 * 高吞吐量的gc 适合高效利用cpu的时间
 * 并且可以自适应调节，这点parnew是没有的，可以根据当前系统的运行
 * 情况动态调整参数提供最合适的停顿时间或最大吞吐量
 * -Xms5m -Xmx5m -XX:+UseParallelGC -XX:+PrintCommandLineFlags -XX:+PrintGCDetails
 * -XX:ParallelGCThreads=n  表示启动多少个线程gc  默认和cpu数相同
 * 且老年parallel 和parallel是互相激活的，配置一个另一个就是默认的了
 *
 * @author dengzidi
 */
public class ParallelGCDemo {

    public static void main(String[] args) {
        String string="aaa";
       while (true){
            string+= string+new Random().nextInt(3333333)+new Random().nextInt(55555555);
          // System.out.println(string);
            //string.intern();
        }




    }
}
