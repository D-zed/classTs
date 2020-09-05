package zhouyang.juc;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * transit修饰
 * https://www.jianshu.com/p/056dc2a53773?utm_campaign=maleskine&utm_content=note&utm_medium=reader_share&utm_source=weixin
 *
 * https://www.cnblogs.com/Profound/p/10879101.html
 *
 * concurrenthashmap分析
 * https://www.cnblogs.com/Profound/p/10930523.html
 * @author dzd
 */
public class ConcurrentDemo {

    public static void main(String[] args) {
        ConcurrentHashMap<String,String> map=new ConcurrentHashMap<>();

        map.put("x","d");

        map.put("xx","d");


        map.putIfAbsent("haha","haha");

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
