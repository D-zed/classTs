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
        //��������o1 o2����item
        //��������h�����ǰ��  h��ȵ�ʱ�� kС����ǰ��  ���� 1-2 ���� 2-1
        Arrays.sort(people, ((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]));


        //Ȼ������ѭ�����������Ȼ�� С���� �������뵽��Ӧ��λ�ã���������������������
        //һ�з�����Ӷ�����Ϊ֮ǰ�ĺ�������  ,��������ˣ���ߵ���ô�������ܲ��뵽���Լ������Ӧ�ط�
        LinkedList<int[]> list = new LinkedList<>();
        //�����ź����people
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
