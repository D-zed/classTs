package backtrace.threesum;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
//全网最好的实现
public class NB {

    /*
    这个算法，我看了一遍竟然没有看懂。后来边调试边看，才懂了作者的意图。
大概记录一下；

首先遍历一下数组，拿到最大和最小值。以及正数的个数和负数的个数以及0的个数。
如果有三个0，那么直接把三个0放到结果的list 里面。
如果正数或者负数集合里面，有一个集合是空的，说明不可能再有结果。
走到这一步，说明，数组里面有正数有负数。那么，最小值的2倍 （一定是负的，因为最小值一定是负责）+ 最大值大于0，
说明数组中存在的最大值没有用，因为没有可能和其他任何两个数加起来等于0.这时候，理论上的最大值边界是-minValue * 2。只有在这个区间的数，才有可能加起来等于0.同理，算出理论上的最小值。
用了三个数组，一个正数数组，一个负数数组，一个map 用来记录比最小值大n 的数是否有。
排序数组
循环负数的数组，得到一个值，然后除以2，得到一个中间值，然后找到比这个数中间值大的位置。从这个位置往后遍历。
然后算出0 减去这两个数的差值是多少。如果这个差值正好等于当前的正数或者负数，那么看下这个正数或者负数的个数是不是>1 个，如果是，说明存在多个该值，那么就把该组数据放到结果里面。如果都不是，
那么去看下这个差值在数组里面是否存在，如果存在返回该组合，不存在不管。
我的算法1000多毫秒，最快的是30毫秒，将近30多倍的效率。
     */

    //int  [] num={-1, 0, 1, 2, -1, -4,6,-2};
    List<Integer> numList = new ArrayList<>();

    Integer[] num = null;

    @Before
    public void before() {
        for (int i = 0; i < 300; i++) {
            if (i % 2 == 0) {
                Double random = Math.random() * 100;
                numList.add(random.intValue());
            } else {
                Double random = Math.random() * 100;
                numList.add(-random.intValue());
            }
        }
        num = numList.toArray(new Integer[numList.size()]);
        System.out.println("num 初始化 ："+JSON.toJSONString(num));
    }

    @Test
    public void test1() {
        long start = System.currentTimeMillis();

        List<List<Integer>> lists = threeSum(num);


        long end = System.currentTimeMillis();
        System.out.println(JSON.toJSONString(lists));
        System.out.println("time :" + (end - start));

    }

    public List<List<Integer>> threeSum(Integer[] nums) {
        if (nums.length < 3){
            //首先排除不足三个的情况
            return Collections.emptyList();
        }
        //结果集合
        List<List<Integer>> res = new ArrayList<>();
        //分别定义上下边界
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        System.out.println("minValue "+minValue+"maxValue :"+maxValue);
        int negSize = 0;
        int posSize = 0;
        int zeroSize = 0;
        //便利数组
        for (int v : nums) {
            //循环一圈得到最小值
            if (v < minValue)
                minValue = v;
            //循环一圈得出最大值
            if (v > maxValue)
                maxValue = v;
            //记录正数的个数
            if (v > 0)
                posSize++;
            //记录负数的个数
            else if (v < 0)
                negSize++;
            //记录零的个数
            else
                zeroSize++;
        }
        //如果 零的个数大于3 那么必有 000
        if (zeroSize >= 3)
            res.add(Arrays.asList(0, 0, 0));
        //如果证书或者负数有一种情况有0，那么直接返回
        if (negSize == 0 || posSize == 0)
            return res;
        //以下两步是重新定义边界
        //如果最大值大于最小值的两倍  那么最大值肯定不在结果中 则最大值重新赋值
        if (minValue * 2 +  maxValue> 0)
            maxValue = Math.abs(minValue * 2) ;
        //如果最小值大于最大值的二倍则 重新赋值最小值 todo 这里需要尝试修改
        else if (maxValue * 2 + minValue < 0)
            minValue = -maxValue * 2;

        //创建最大值和最小值区间的数组
        int[] map = new int[maxValue - minValue + 1];
        //创建正数数组
        int[] negs = new int[negSize];
        //创建负数数组
        int[] poses = new int[posSize];
        negSize = 0;
        posSize = 0;
        //开始筛选正数负数 并且在map数组中记录正数位置的数组
        for (int v : nums) {
            //判断值在最大最小区间的
            if (v >= minValue && v <= maxValue) {
                //当前值减最小值
                int sub = v - minValue;
                //核心逻辑 map记录个数  位置使用插值
                if (map[sub]++ == 0) {
                    if (v > 0)
                        //此时加入代表筛选
                        poses[posSize++] = v;
                    else if (v < 0)
                        negs[negSize++] = v;
                }
            }
        }
        //分别排序
        Arrays.sort(poses, 0, posSize);
        Arrays.sort(negs, 0, negSize);
        int basej = 0;
        //遍历负数数组
        for (int i = negSize - 1; i >= 0; i--) {
            //负数item
            int nv = negs[i];
            //将item除以2
            int minp = (-nv) >>> 1;
            //基准值走到此时负数的绝对值的二分之一
            while (basej < posSize && poses[basej] < minp)
                basej++;
            //边界定了 从边界向后找
            for (int j = basej; j < posSize; j++) {
                //此时的正数
                int pv = poses[j];
                //此时正数负数都在了 计算出应该的那个正数是多少
                int cv = 0 - nv - pv;
                if (cv >= nv && cv <= pv) {
                    if (cv == nv) {
                        //使用数组当map 使用插值找对应的值，有点东西啊  此处之所以是大于1是因为 此值一定要求有两个才算
                        if (map[nv - minValue] > 1)
                            //如果找到了直接加入即可
                            res.add(Arrays.asList(nv, nv, pv));
                    } else if (cv == pv) {
                        //如果 结果值是等于 负数 则在去map中获取对应的东西 如果有则取出来
                        if (map[pv - minValue] > 1)
                            res.add(Arrays.asList(nv, pv, pv));
                    } else {
                        //如果其他情况则 用预计值获取 只要大于0即可
                        if (map[cv - minValue] > 0)
                            res.add(Arrays.asList(nv, cv, pv));
                    }
                } else if (cv < nv)
                    break;
            }
        }
        return res;

    }
}
