package leetcode.backtrace;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BackTranceTs1111 {

    //https://zhuanlan.zhihu.com/p/93530380
    //回溯核心就是递归 选择 撤销 算法

    //找出nums 的 所有的子集

    List<List<Integer>> resultList=new ArrayList<>();

    @Test
    public void test1(){

        int [] nums={1,2,3};
        List<Integer> tmpList=new ArrayList<>();
        backTrace(resultList,tmpList,0,nums);

        System.out.println(resultList.toString());
    }

    private void backTrace(List<List<Integer>> resultList, List<Integer> tmpList, int start, int[] nums) {
        resultList.add(new ArrayList<>(tmpList));
        for (int j = start; j < nums.length; j++) {
            tmpList.add(nums[j]);
            backTrace(resultList,tmpList,j+1,nums);
            tmpList.remove(tmpList.size()-1);
        }
    }


}