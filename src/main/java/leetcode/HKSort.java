package leetcode;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

public class HKSort {


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

    private int[][] sortQueue(int[][] people) {
        //这个排序的o1 o2都是item
        //首先排序h大的在前边  h相等的时候 k小的在前边  正排 1-2 倒排 2-1
        Arrays.sort(people, ((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]));


        //然后再遵循大个子先排序，然后 小个子 再来插入到对应的位置，由于是链表可以随意插入
        //一切放心添加都是因为之前拍好了序了  ,大的现在了，后边的怎么来都是能插入到比自己大的相应地方
        LinkedList<int[]> list = new LinkedList<>();
        //上面排好序的people
        for (int[] person : people) {
            list.add(person[1], person);
        }

        return list.toArray(new int[list.size()][2]);
    }

    public static void main(String[] args) {
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentTimeMillis);
    }
}
