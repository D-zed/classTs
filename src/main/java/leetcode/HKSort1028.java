package leetcode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 假设有打乱顺序的一群人站成一个队列，每个人由一个整数对（h,K）
 * 表示 h表示身高 k表示排在这个人前面且身高大于或等于h的人数，编写一个算法重新构建这个队列
 */
public class HKSort1028 {


    @Test
    public void test1() {
        //   h k
        int[][] people = {
                {7, 0},
                {7, 1},
                {6, 1},
                {5, 0},
                {5, 2},
                {4, 4}
        };
        int[][] peopleQueue = sortQueue(people);

        System.out.println(JSONObject.toJSONString(peopleQueue));
    }

    private  int[][] sortQueue(int[][] people) {
        //首先将people排序 大的在前，同样的h 按照k排序  1减2正序 2减1倒叙
        Arrays.sort(people,((o1, o2) -> o1[0]==o2[0]?o1[1]-o2[1]:o2[0]-o1[0]));

        System.out.println("people 第一次排序"+ JSON.toJSONString(people));

        //然后对最终的数据进行
        List<int[]> peopleList=new LinkedList<>();
        for (int i = 0; i < people.length; i++) {
             peopleList.add(people[i][1],people[i]);
        }
        //首先
        return    peopleList.toArray(new int[peopleList.size()][2]);

    }
}
