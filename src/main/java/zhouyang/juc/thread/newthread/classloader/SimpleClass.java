package zhouyang.juc.thread.newthread.classloader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dzd
 */
public class SimpleClass {

    private static byte [] buffer=new byte[8];

    private static String str="";

    private static List<String> list=new ArrayList<>();

    static {
        buffer[0]=1;
        str ="Simple";
        list.add("element");
        System.out.println(buffer[0]);
        System.out.println(str);
        System.out.println(list.get(0));
    }
}
