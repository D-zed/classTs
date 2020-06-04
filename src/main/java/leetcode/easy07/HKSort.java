package leetcode.easy07;

import org.junit.Test;

public class HKSort {


    /*
    * 题意是给你一个整型数，求它的逆序整型数，而且有个小坑点，当它的逆序整型数溢出的话，那么就返回 0，用我们代码表示的话可以求得结果保存在 long 中，
    * 最后把结果和整型的两个范围比较即可。
    * */
    @Test
    public void test1() {
        long reverse = reverse(568870);
        System.out.println(reverse);
    }

    public long  reverse(int input){
        long res=0;
        while (input!=0){
            res=res*10+input%10;
            input=input/10;
        }

        return res>Integer.MAX_VALUE || res<Integer.MIN_VALUE ? 0:res;

    }




}
