package leetcode.easy09;

import org.junit.Test;

public class HKSort09 {


    /*
    * 题意是判断一个有符号整型数是否是回文，也就是逆序过来的整数和原整数相同，首先负数肯定不是，接下来我们分析一下最普通的解法，
    * 就是直接算出他的回文数，然后和给定值比较即可
    * */

    public static void main(String[] args) {
        int i = reverseNum(567);
        System.out.println(i);
    }

    private static int reverseNum(int num) {
        int result=0;
        while (num>0||num/10!=0){
            int i = num % 10;
           result= result*10+i;
           num=num/10;
        }
        return result;
    }


}
