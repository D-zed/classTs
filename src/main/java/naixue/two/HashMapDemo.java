package naixue.two;

import util.fastjson.JsonUtils;

import java.util.HashMap;

/**
 * 问题环形链表怎么产生
 *
 * java 8的hash 有两次扰动
 * java 7的hash you9次 =4次位运算+5次异或
 * 0^0=0,0^1=1 可理解为：0异或任何数，其结果=任何数
 * <p>
 * 1^0=1,1^1=0 可理解为： 1异或任何数，其结果=任何数取反
 * <p>
 * <p>
 * 1100001000001101  0001000001111111
 * 0000000000000000  1100001000001101
 *
 * 一句话总结为什么要求是2的n次方
 * &当被与数为2^n-1的时候相当于取余
 *
 * hashmap的resize部分
 * https://segmentfault.com/a/1190000015812438?utm_source=tag-newest
 * @author dzd
 */
public class HashMapDemo {

    public static void main(String[] args) {
        int i5 = myTableSizeFor(17);
        System.out.println(i5);

        int i4 = tableSizeFor(17);
        System.out.println(i4);

        HashMap<String, String> map = new HashMap<>();

        String key = "aa";
        String val = "bb";
        String val2 = "cc";
        map.put(key, val);
        String put = map.put(key, val2);
        System.out.println("返回oldval bb：" + put);
        System.out.println(JsonUtils.toJsonFormat(map));
        //hashcode
        int hashCode = key.hashCode();
        // hashcode的二进制
        String hashCodeBin = Integer.toBinaryString(hashCode);

        //右移16位 之后高16位都是0
        int hashCode16 = hashCode >>> 16;
        //高16位和低16位异或 一样为零不一样是1
        String hashCode16Bin = Integer.toBinaryString(hashCode16);

        System.out.println(hashCode);
        System.out.println(hashCodeBin);
        System.out.println(hashCode16);
        System.out.println(hashCode16Bin);
        /**
         * hashcode的高16位异或低16位的作用是使得hashcode的低16位更加散列，尽量分散，而且位运算效率很高
         * 散列性也由此变高，因为hashCode和n进行与运算，起始也就是最后的几位决定了hash表中的位置，所以低十六位
         * 的散列使得最终的散列性变好了
         */
        int i = hashCode ^ hashCode16;
        System.out.println(i);

        //16
        System.out.println("2的4次方:" + Integer.toBinaryString(16));
        ;
        System.out.println("2的4次方减1:" + Integer.toBinaryString(16 - 1));
        System.out.println("15的二进制:" + Integer.toBinaryString(15));
        ;
        System.out.println("14的二进制:" + Integer.toBinaryString(15 - 1));
        ;
        /**
         * 高16位和低16位异或是增加散列概率
         * 扩容后的容量必须得是2^n 是因为
         * 是需要计算index  index = (n - 1) & hash 遇0则0 所以如果出现了例如15，15-1=14，那么14的2进制如下
         * 1110 ，如果出现了这种情况，那么我们的hash数组中的其中一个位置永远不会有值也就浪费了空间
         */
        //二进制字符串传为十进制
        String all = "11000010000011010001000001111111";
        //解析无符号的二进制
        int i1 = Integer.parseUnsignedInt(all, 2);
        String all16 = "00000000000000001100001000001101";
        int i2 = Integer.parseUnsignedInt(all16, 2);
        int i3 = i1 ^ i2;
        System.out.println(Integer.toBinaryString(i3));

    }


    public static String toBin(int num) {
        StringBuilder sb = new StringBuilder();
        if (num == 0) {
            return "";
        } else {
            sb.append(toBin(num / 2) + num % 2);
        }
        return sb.toString();
    }
    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        //这种右移的次数就可以满足最终所有位置填补为1
        //无符号右移>>>(不论正负,高位均补0)
        //第一次位移可以有两个1第二次位相当移缩进两个，与之前的两个再次或也就有了四个1 ，然后再缩进四个 ，或之后变成8个，依次类推满足32位
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }


    /**
     * 对hashmap源码添加了一些日志，把代码进行了分解
     * @param cap
     * @return
     */
    static final int myTableSizeFor(int cap) {

        int n = cap - 1;
        System.out.println("初始的n："+Integer.toBinaryString(n));
        System.out.println("右移的n："+Integer.toBinaryString(n >>> 1));
        n |= n >>> 1;
        System.out.println("计算后的n："+Integer.toBinaryString(n));
        System.out.println("初始的n："+Integer.toBinaryString(n));
        System.out.println("右移的n："+Integer.toBinaryString(n >>> 2));
        n |= n >>> 2;
        System.out.println("计算后的n："+Integer.toBinaryString(n));
        System.out.println("初始的n："+Integer.toBinaryString(n));
        System.out.println("右移的n："+Integer.toBinaryString(n >>> 4));
        n |= n >>> 4;
        System.out.println("计算后的n："+Integer.toBinaryString(n));
        System.out.println("初始的n："+Integer.toBinaryString(n));
        System.out.println("右移的n："+Integer.toBinaryString(n >>> 8));
        n |= n >>> 8;
        System.out.println("计算后的n："+Integer.toBinaryString(n));

        System.out.println("初始的n："+Integer.toBinaryString(n));
        System.out.println("右移的n："+Integer.toBinaryString(n >>> 16));
        n |= n >>> 16;
        System.out.println("计算后的n："+Integer.toBinaryString(n));


        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
