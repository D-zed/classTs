package leetcode.easy.easy01;

import java.util.HashMap;

/**
 * 两数和问题
 * @author dzd
 */
public class TwoNumSum {

   static int[] nums = {2, 7, 11, 15,1,8};

    public static void main(String[] args) {
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        int sum=9;
        for (int i = 0; i < nums.length; i++) {
            Integer integer = hashMap.get(sum - nums[i]);
            if (integer==null){
                hashMap.put(nums[i],i);
            }
            else {
                System.out.println("两数和为"+sum+"的位置分别为 "+i+","+integer);
            }

        }
    }

}