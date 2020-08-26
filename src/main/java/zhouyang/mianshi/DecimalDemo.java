package zhouyang.mianshi;

import java.math.BigDecimal;

public class DecimalDemo {
    public static void main(String[] args) {
        //这种会出现精度问题
       // BigDecimal bigDecimal1 = new BigDecimal(5.2);
        BigDecimal bigDecimal2 = new BigDecimal("5.1");
        BigDecimal bigDecimal=BigDecimal.valueOf(5.299);
        Double dd=5.1;
        System.out.println(bigDecimal);
    //    System.out.println(bigDecimal1);

        System.out.println(bigDecimal2);
        System.out.println(dd);
        BigDecimal add = bigDecimal.add(bigDecimal2);
        System.out.println(add);

        //stringCache  使用tostring的时候使用的是这个缓存，如果缓存不存在会首先构建缓存 通过 intCompact
        //scale 代表小数位数
        //precision 代表整个数的位数
        //intCompact代表的是一个整数值  比如 5.299 就是 5299的long类型

        //这个相加的原理是通过先将 两个数的位数补平 就是5.1 的intCompact 编程 5100 ，5.299变成 5299
        //然后对5100+5299计算成一个 intCompact 然后再重新new 一个bigDecimal,所以每次相加都会产生一个
        //新的结果
        System.out.println(add.toString());
    }
}
