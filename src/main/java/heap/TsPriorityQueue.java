package heap;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TsPriorityQueue {


    @Test
    public void test1() {
        int a[] = {1, 1, 1, 8, 8, 8, 2, 6, 9, 8, 3};
        int[] ints = topKFrequent(a, 2);

    }

    @Test
    public void test2() {
        int a[] = {1, 1, 1, 8, 8, 8, 2, 6, 9, 8, 3};
        String aa = topKBigHeap(a, 2);
        System.out.println(aa);
    }

    /**
     * С����
     *
     * @param nums nums
     * @param k    k
     * @return int
     */
    public int[] topKFrequent(int[] nums, int k) {
        //ʹ�����ȶ��н���ɸѡ��Ĭ�϶��ǶӶ���С ������дcomparable ��Ӷ���������
        Queue<Obj> queue = new PriorityQueue<>((o1, o2) ->
             o2.num - o1.num
        );

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        for (int key : map.keySet()) {
            queue.offer(new Obj(key, map.get(key)));
        }

        int[] ans = new int[k];
        int i = 0;
        while (i < k) {
            ans[i] = queue.poll().target;
            i++;
        }

        return ans;

    }

    class Obj {
        public int target;
        public int num;

        public Obj(int target, int num) {
            this.target = target;
            this.num = num;
        }
    }


    public String topKBigHeap(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        //�о����ֻ��ǿ����Ҫ�öѣ���ķ���Ҳ����
        //����һ��С����
        PriorityQueue<Integer> queue = new PriorityQueue<>((n1, n2) -> map.get(n1) - map.get(n2));
        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            Integer key = integerIntegerEntry.getKey();
            queue.add(key);
            if (queue.size() > k) {
                //�������ݲ������¶ѻ�
                queue.poll();
            }
        }

        return queue.toString();
    }


}