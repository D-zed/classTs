package zhouyang.jvm.collector;

import java.util.Random;

/**
 * 此个只是新生代用并行老年代还是串行  这个推荐老年代使用cms  达到最优的 新生代复制算法 老年代标记清楚 也可以设置为标记压缩
 * jvm的七大垃圾收集器中的
 * -Xms5m -Xmx5m -XX:+UseParNewGC -XX:+PrintCommandLineFlags -XX:+PrintGCDetails
 * @author dengzidi
 */
public class ParNewDemo {

    public static void main(String[] args) {
        String string="aaa";
       while (true){
            string+= string+new Random().nextInt(3333333)+new Random().nextInt(55555555);
          // System.out.println(string);
            //string.intern();
        }




    }
}
