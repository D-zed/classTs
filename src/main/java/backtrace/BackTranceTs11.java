package backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 1027此数据对应的是 通过程序列举出来一个集合的所有的子集
 *
 * @author dzd
 */
public class BackTranceTs11 {

    //https://zhuanlan.zhihu.com/p/93530380
    //回溯核心就是递归 选择 撤销 算法

    //nums 的 所有的子集

    static List<List<Integer>> resultList = new ArrayList<>();

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        List<Integer> temp = new ArrayList<>();
        backTrance(0, nums, temp);


        System.out.println(resultList.toString());
    }


    private static void backTrance(int start, int[] nums, List<Integer> temp) {

        resultList.add(new ArrayList<>(temp));

        for (int i = start; i < nums.length; i++) {
            //选择
            temp.add(nums[i]);
            //递归i
            backTrance( i+1, nums, temp);
            //撤销i
            temp.remove(temp.size() - 1);
        }
    }
}