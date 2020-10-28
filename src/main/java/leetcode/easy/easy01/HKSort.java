package leetcode.easy.easy01;

import org.junit.Test;

import java.util.HashMap;

public class HKSort {


    /*Given nums = [2, 7, 11, 15], target = 9,

    Because nums[0] + nums[1] = 2 + 7 = 9,
            return [0, 1].*/
//题意是让你从给定的数组中找到两个元素的和为指定值的两个索引
    @Test
    public void test1() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        HashMap<Integer, Integer> amap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            amap.put(target - nums[i], nums[i]);
        }

        for (int i = 0; i < nums.length; i++) {
            Integer integer = amap.get(nums[i]);
            if (integer != null) {
                System.out.println(i);
            }
        }
    }

    //一次循环就能搞定
    @Test
    public void test2() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        HashMap<Integer, Integer> amap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            Integer integer = amap.get(nums[i]);
            if (integer != null) {
                System.out.println(i + " " + integer);
            }
            //值为下标
            amap.put(target - nums[i], i);
        }
    }

    @Test
    public void test3() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        HashMap<Integer, Integer> amap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            Integer integer = amap.get(target - nums[i]);
            if (integer==null){
                amap.put(nums[i],i);
            }else {
                System.out.println(i+" "+integer);
                amap.remove(integer);
            }
        }

    }


}
