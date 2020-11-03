package leetcode.backtrace.threesum;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;


/**
 * 我自己的实现  单纯的练习了下回溯算法，时间复杂度比n^3的暴力破解法小一点 但是  也达到了  n*（n-1）*(n-2) 而且是真 的时间 不是最差时间复杂度
 *
 * 回溯
 * @author dzd
 */
public class BackTranceTs5 {
    //三数之和问题三数之和  给定的数组中三数之和等于0的所有情况
//网上最快的代码  https://www.cnblogs.com/caoxinyu/p/10568505.html

    //如何判断两个集合相等 https://blog.csdn.net/qq_35640964/article/details/87881714?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase
    //在此得到的灵感  https://blog.csdn.net/qq_21104515/article/details/79396782?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase

    //还需要看下别人怎么实现的  https://blog.csdn.net/qq_17550379/article/details/80614597?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1.nonecase

    List<Integer> numList = new ArrayList<>();

    Integer[] num = null;

    @Before
    public void before() {
        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0) {
                Double random = Math.random() * 100;
                numList.add(random.intValue());
            } else {
                Double random = Math.random() * 100;
                numList.add(-random.intValue());
            }
        }
        num = numList.toArray(new Integer[numList.size()]);
        System.out.println(JSON.toJSONString(num));
    }


    @Test
    public void test1() {
        long start = System.currentTimeMillis();
        List<Integer> tmpList = new ArrayList<>();
        transBack(tmpList, 0);
        System.out.println(JSON.toJSONString(resultList));

        HashSet<List<Integer>> set = new HashSet<>();
        for (List<Integer> integers : resultList) {
            Collections.sort(integers);
            set.add(integers);
        }
        long end = System.currentTimeMillis();
        System.out.println("time :" + (end - start));
        //System.out.println(JSON.toJSONString(resultList));
        // System.out.println(set);
    }

    List<List<Integer>> resultList = new ArrayList<>();

    public void transBack(List<Integer> tmpList, int start) {
        if (tmpList.size() == 3) {
            int sum = tmpList.stream().mapToInt(itm -> itm).sum();
            if (sum == 0) {
                // System.out.println("满足条件"+JSON.toJSONString(tmpList));
                resultList.add(new ArrayList<>(tmpList));
            }
            return;
        }

        for (int i = start; i < num.length; i++) {
            tmpList.add(num[i]);
            transBack(tmpList, i + 1);
            tmpList.remove(tmpList.size() - 1);
        }
    }


}
