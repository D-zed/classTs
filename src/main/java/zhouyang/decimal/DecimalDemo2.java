package zhouyang.decimal;

import java.math.BigDecimal;

/**
 * https://www.cnblogs.com/happy520/p/7090199.html
 *
 * @author dzd
 */

public class DecimalDemo2 {

    public static void main(String[] args) {

        BigDecimal bigDecimal = new BigDecimal("30.21");
        BigDecimal bigDecimal11 = new BigDecimal("30.210");
        BigDecimal bigDecimal1 = new BigDecimal(30.21);
        //此二者皆不相等 证明了使用 30.21使得精度丢失
        System.out.println(bigDecimal.compareTo(bigDecimal1)==0);
        System.out.println(bigDecimal.equals(bigDecimal1));

        //此方法不等说名此equals比较的不只是真实值，还有精度
        System.out.println(bigDecimal.equals(bigDecimal11));
        //这个比较的才是两个数的值
        System.out.println(bigDecimal.compareTo(bigDecimal11)==0);
        //---------------------------------------------------------------------
        System.out.println("one--------------------------------------------");
        BigDecimal one = BigDecimal.ONE;
        BigDecimal one2 = new BigDecimal("1");
        BigDecimal one3 = BigDecimal.valueOf(1);
        //一下都相等所以说明这几种方式创建BigDecimal都是不丢失精度的
        System.out.println(one.equals(one2));
        System.out.println(one.compareTo(one2)==0);

        System.out.println(one.equals(one3));
        System.out.println(one.compareTo(one3)==0);
        System.out.println("1.30------------------------------------------");
        BigDecimal one22 = new BigDecimal("1.30");
        //可以手动指定精度，这样精度和值就都一样了
        BigDecimal one33 = BigDecimal.valueOf(130,2);
        System.out.println(one22.equals(one33));
        System.out.println(one22.compareTo(one33)==0);
        System.out.println("divide-------------------------------------");
        BigDecimal subtract = bigDecimal.subtract(BigDecimal.ONE);
        //设置四舍五入保留两位
        System.out.println("设置四舍五入保留两位--------------------------------------");
        subtract = subtract.divide(new BigDecimal("11"), 2, BigDecimal.ROUND_HALF_UP);
        System.out.println("四舍五入的结果 " + subtract);

        BigDecimal aa = subtract.multiply(new BigDecimal("11")).add(BigDecimal.ONE);
        System.out.println(aa);

        System.out.println("打印方式------------------------------");
        //这个对于我目前所见的程序toString就ok了
        //bigDecimal的打印方式
        BigDecimal b = new BigDecimal("1E11");
        //这个是 工程计数法
        String s = b.toEngineeringString();
        System.out.println(s);
        //科学计数法
        String s1 = b.toString();
        System.out.println(s1);
        //这个是原样子的
        String s2 = b.toPlainString();
        System.out.println(s2);
    }
}