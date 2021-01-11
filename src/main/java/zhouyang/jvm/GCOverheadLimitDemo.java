package zhouyang.jvm;

import java.util.ArrayList;

/**
 * 第二种oom gc回收效率低下造成过多的无效gc，也就是gc过头了 gc overhead limit
 * 超过98的时间来做gc 却只能回收2%
 * @author dengzidi
 */
public class GCOverheadLimitDemo {


    /**
     * gc参数
     * -Xms10m -Xmx10m -XX:+PrintGCDetails
     * @param args args
     */
    public static void main(String[] args) {
        int i=0;
        ArrayList<String> objects = new ArrayList<>();
        try {
            while (true){
                //此处应该是因为
                objects.add(String.valueOf(++i));
            }
        }catch (Throwable throwable){
            System.out.println("******* i "+i);
            throwable.printStackTrace();
            throw  throwable;
        }

    }
}
