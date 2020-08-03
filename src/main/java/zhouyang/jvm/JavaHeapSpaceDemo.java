package zhouyang.jvm;

import java.util.Random;

/**
 * 堆内存溢出演示
 * java.lang.OutOfMemoryError: Java heap space
 * @author dengzidi
 */
public class JavaHeapSpaceDemo {


    /**
     * intern详解
     * https://www.cnblogs.com/naliyixin/p/8984077.html
     * @param args
     */
    public static void main(String[] args) {

        String str="dzd";
        while (true){
            str+=str+new Random().nextInt(11111111)+new Random().nextInt(222222222);
            str.intern();
        }
    }
}
