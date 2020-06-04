package second;

import java.math.BigInteger;

public class Ts1 {

   public static void main(String[] args) {

      //  1.利用while循环计算一个正整数的二进制格式。
        int a=10;
        StringBuffer  sb=new StringBuffer();   //
        while (a>0){
        int b=  a%2;
             a= a/2;
         sb.insert(0, b);//插入到指定偏移位置
        }
        System.out.println("二进制"+sb);
System.out.println("tobinary"+Integer.toBinaryString(10));
System.out.println("tostring"+Integer.toString(10,2));
      //  2.利用while循环计算一个正整数的三进制制格式。
        int ab=10;
        StringBuffer  sbb=new StringBuffer();   //
        while (ab>0){
            int b=  ab%3;
            ab= ab/3;
            sbb.insert(0,b);
        }
        System.out.println("三进制"+sbb);
       // Byte  ccdc=new Byte(sb.toString());
       // System.out.println(ccdc);
       // 3.利用位运算计算一个数除以8的结果
        int dd=8;
       System.out.println(dd>>3);
      //  4.利用位运算计算一个数乘以8的结果
        System.out.println(dd<<3);

        for (String arg : args) {
            //iter快捷键
        }
        //  5.利用位运算计算一个数除以8后的余数。

System.out.println(ts5(15)+"di5");
    }
    public  static  int  ts5(int aa){

        return    aa- ((aa>>3)<<3);



    }public  static  void  ts15( ){

        short s1=1;
        short s2=1;
               s1=(short) (s1+1);

               short s3= (short) (s1+s2);
            s1+=1;

    }

}
