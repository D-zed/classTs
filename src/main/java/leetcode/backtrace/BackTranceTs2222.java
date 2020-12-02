package leetcode.backtrace;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BackTranceTs2222 {

    List<List<Integer>> resultList = new ArrayList<>();

    //得到nums的所有的排列组合
    @Test
    public void test2() {
        int[] nums = {1, 2, 3};
        List<Integer> tmpList=new ArrayList<>();
        backTrace(nums,tmpList);
        System.out.printf(resultList.toString());
    }

    private void backTrace(int[] nums, List<Integer> tmpList) {
        if (tmpList.size()==nums.length){
            resultList.add(new ArrayList<>(tmpList));
        }
        for (int i = 0; i < nums.length; i++) {
            if (tmpList.contains(nums[i])){
                continue;
            }
            tmpList.add(nums[i]);
            backTrace(nums,tmpList);
            tmpList.remove(tmpList.size()-1);
        }

    }


}
