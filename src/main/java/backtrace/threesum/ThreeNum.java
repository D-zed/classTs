package backtrace.threesum;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

//我自己的实现
public class ThreeNum {

    //int  [] num={-1, 0, 1, 2, -1, -4,6,-2};
    List<Integer> numList = new ArrayList<>();

    Integer[] num = null;

    @Before
    public void before() {
        for (int i = 0; i < 30000; i++) {
            if (i % 2 == 0) {
                Double random = Math.random() * 100;
                numList.add(random.intValue());
            } else {
                Double random = Math.random() * 100;
                numList.add(-random.intValue());
            }
        }
        num = numList.toArray(new Integer[numList.size()]);
        System.out.println("num 初始化 ：" + JSON.toJSONString(num));
    }

    @Test
    public void test1() {
        long start = System.currentTimeMillis();

        List<List<Integer>> lists = threeSum(num);


        long end = System.currentTimeMillis();
        System.out.println(JSON.toJSONString(lists));
        System.out.println("time :" + (end - start));

    }

    private List<List<Integer>> threeSum(Integer[] num) {
        List<List<Integer>> resultList = new ArrayList<>();
        //key 值  integer是个数
        Map<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < num.length; i++) {
            Integer integer = map.get(num[i]) == null ? 0 : map.get(num[i]);
            map.put(num[i], integer + 1);
        }

        if (map.get(0)!=null&&map.get(0)>= 3) {
            resultList.add(Arrays.asList(0, 0, 0));
        }
        //正数集合
        Set<Integer> posNum = new HashSet<>();
        Set<Integer> nagNum = new HashSet<>();

        //正数集合
        Set<Integer> posNumFor = new HashSet<>();
        Set<Integer> nagNumToPos = new HashSet<>();
        for (Integer integer : num) {
            if (integer.compareTo(0) > 0) {
                posNum.add(integer);
                posNumFor.add(integer);
            } else if (integer.compareTo(0)<0){
                nagNum.add(integer);
                nagNumToPos.add(-integer);
            }
        }
        if (map.get(0) != null && map.get(0) > 0) {
            posNumFor.retainAll(nagNumToPos);
            //此时一部分交际就计算出来了并且可以考虑把零去掉了
            for (Integer integer : posNumFor) {
                resultList.add(Arrays.asList(0, integer, -integer));
            }
        }

        //最后来计算最终的内容也就是把零删除的情况
        //完全考虑正负数的问题
        //可以考虑优化 todo 将绝对值最大的负数两倍 还大的正数移除 目前不做
        //遍历负数
        for (Integer nag : nagNum) {
            for (Integer pos : posNum) {
                int tmp = 0 - nag - pos;
                Integer count = map.get(tmp);
                if (count == null) {
                    continue;
                }
                if (tmp == nag || tmp == pos) {
                    if ( count<2){
                        continue;
                    }
                }
                resultList.add(Arrays.asList(nag,pos,tmp));
            }
        }


        return resultList;
    }

}
