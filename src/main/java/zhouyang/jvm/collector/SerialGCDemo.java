package zhouyang.jvm.collector;

import java.util.Random;

/**
 * 新生代复制 老年代标记整理
 * 新生代串行 老年代还是串行
 * jvm的七大垃圾收集器中的
 * -Xms5m -Xmx5m -XX:+SerialGC -XX:+PrintCommandLineFlags -XX:+PrintGCDetails
 * @author dengzidi
 */
public class SerialGCDemo {

    public static void main(String[] args) {
        String string="aaa";
       while (true){
            string+= string+new Random().nextInt(3333333)+new Random().nextInt(55555555);
          // System.out.println(string);
            //string.intern();
        }




    }
}
