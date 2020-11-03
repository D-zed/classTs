package leetcode.backtrace;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BackTranceTs1 {

    //https://zhuanlan.zhihu.com/p/93530380
    //回溯核心就是递归 选择 撤销 算法

    //nums 的 所有的子集

    List<List<Integer>> resultList=new ArrayList<>();

    @Test
    public void test1(){

        int [] nums={1,2,3};
        List<Integer> temp=new ArrayList<>();
        backTrace(0,nums,temp);

        System.out.println(resultList.toString());
    }


    public void backTrace(int start,int [] num,List<Integer> temp){
        resultList.add(new ArrayList<>(temp));
        for (int i = start; i < num.length; i++) {
            temp.add(num[i]);
            backTrace(i+1,num,temp);
            temp.remove(temp.size()-1);
        }
    }
}