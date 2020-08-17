package leetcode.easy09;

import org.junit.Test;

public class HKSort {


    /*
    * 题意是判断一个有符号整型数是否是回文，也就是逆序过来的整数和原整数相同，首先负数肯定不是，接下来我们分析一下最普通的解法，
    * 就是直接算出他的回文数，然后和给定值比较即可
    * */

    //我的思路抓换为字符串
    @Test
    public void test1() {

        //转换为字符串
        Boolean huiWen = isHuiWen(-225522);
        System.out.println(huiWen);
    }

    @Test
    public void test2(){
        //先求回文数 然后对比就ok了   hiahia 这个是大佬的思路0；

        Boolean huiWen2 = isHuiWen2(77777);
        System.out.println(huiWen2);
    }


    @Test
    public void test3(){
        //优化之后我们只需要比较一半即可
        //逆数大于正数的时候即可
    }

    public Boolean isHuiWen2(int input){

        int init =input;
        if (input<0){
            return false;
        }
        int result=0;
        for (; input !=0; input/=10) {

            result=result*10+input%10;

        }
        if (result==init){
            return true;

        }
        return false;
    }


    public Boolean isHuiWen(int input){

        String s = input + "";
        char[] chars = s.toCharArray();
        int length = chars.length-1;
        for (int i = 0; i <=length; i++) {
            int i1 = length - i;
            if (chars[i]!=chars[i1]){
              return false;
            }
        }
        return true;
    }




}
