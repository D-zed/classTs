package zhouyang.juc;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * transit修饰
 * https://www.jianshu.com/p/056dc2a53773?utm_campaign=maleskine&utm_content=note&utm_medium=reader_share&utm_source=weixin
 *
 * https://www.cnblogs.com/Profound/p/10879101.html
 *
 * concurrenthashmap putIfAbsent方法如果存在则返回原来的值，如果不存在则返回null，并且不会覆盖 其中逻辑加锁线程安全
 * https://www.cnblogs.com/Profound/p/10930523.html
 * @author dzd
 */
public class ConcurrentDemo {

    public static void main(String[] args) {
        ConcurrentHashMap<String,String> map=new ConcurrentHashMap<>();

        String put1 = map.put("x", "d");

        System.out.println(put1);
        String put = map.put("xx", "d");

        System.out.println(put);

        String s = map.putIfAbsent("x", "haha");
        System.out.println(s);
        String s1 = map.putIfAbsent("xxx", "haha");
        System.out.println(s1);
        //HashMap<String,String> map=new HashMap<>();
        //map.put("dd","dd");

        /**
         * 这个地方是对hashcode值做一个处理使用高16位和低16位异或的方式，使得低16位的数字不在单一，否则如果直接进行与操作
         * 那我我们也知道 与是 遇零则零的，这样可能造成好多的hash冲突
         *
         * 混合原始hash码的高低位，增加随机性
         *
         * 以及数组长度为什么要求是2的n次方呢 因为(n - 1) & hash 计算下标位置的时候是与运算 其遇0则零，如果当我们的数字不是2的n次方
         * 则 减去1之后 就不会变成 1111111这种格式，则其hash的位置冲突就会很多，有很多未知甚至都不会被分配到
         */
        //int hash = spread(key.hashCode());
    }


}
