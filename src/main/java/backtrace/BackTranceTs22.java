package backtrace;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BackTranceTs22 {

    List<List<Integer>> resultList = new ArrayList<>();

    //所有的排列
    @Test
    public void test2() {
        int[] nums = {1, 2, 3};
        List<Integer> temp=new ArrayList<>();
        backTrace(nums,temp);
        System.out.printf(resultList.toString());
    }

    private void backTrace(int[] nums, List<Integer> temp) {
         if (temp.size()==nums.length){
             resultList.add(new ArrayList<>(temp));
         }else {

             for (int i = 0; i < nums.length; i++) {
                 if (temp.contains(nums[i])){
                     continue;
                 }else {
                     temp.add(nums[i]);
                     backTrace(nums,temp);
                     temp.remove(temp.size()-1);
                 }
             }
         }
    }


}
