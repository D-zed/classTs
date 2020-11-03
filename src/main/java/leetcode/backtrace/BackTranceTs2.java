package leetcode.backtrace;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BackTranceTs2 {

    List<List<Integer>> resultList = new ArrayList<>();

    //所有的排列组合
    @Test
    public void test2() {
        int[] nums = {1, 2, 3};
        List<Integer> tmp = new ArrayList<>();
        backTrace(nums, tmp);

        System.out.printf(resultList.toString());
    }


    public void backTrace(int[] num, List<Integer> tmp) {
        if (tmp.size() == num.length) {
            resultList.add(new ArrayList<>(tmp));
        } else {
            for (int i = 0; i < num.length; i++) {
                if (tmp.contains(num[i])) {
                    continue;
                }
                //选择
                tmp.add(num[i]);
                //递归
                backTrace(num, tmp);
                System.out.println("tmp ->" + tmp.toString() + "i->" + i);
                //它移除tempList最后一个元素的作用就是返回上一次调用时的数据，也就是希望返回之前的节点再去重新搜索满足条件。这样才能实现回溯
                //此处作为上一步的还原点，并且在最开始的判断中使用 包含法判断是否已经包含用来跳过
                //撤销
                tmp.remove(tmp.size() - 1);
            }
        }
    }


}
