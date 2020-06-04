package leetcode.easy.easy01;

import org.junit.Test;

import java.util.HashMap;

public class HKSort {


    /*Given nums = [2, 7, 11, 15], target = 9,

    Because nums[0] + nums[1] = 2 + 7 = 9,
            return [0, 1].*/
//����������Ӹ������������ҵ�����Ԫ�صĺ�Ϊָ��ֵ����������
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

    //һ��ѭ�����ܸ㶨
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
            //ֵΪ�±�
            amap.put(target - nums[i], i);
        }
    }


}
