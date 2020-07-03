package backtrace.threesum;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Laji {

    //int  [] num={-1, 0, 1, 2, -1, -4,6,-2};
    List<Integer> numList = new ArrayList<>();

    Integer[] num = null;

    @Before
    public void before() {
        for (int i = 0; i < 500; i++) {
            if (i%2==0) {
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

        List<List<Integer>> lists = threeSum(num);


        long end = System.currentTimeMillis();
        System.out.println(JSON.toJSONString(lists) + "time :" + (end - start));

    }

    public List<List<Integer>> threeSum(Integer[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        boolean needAdd = true;
        boolean hasZore = false;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {

//                        long l = System.nanoTime();
                        needAdd = true;
                        if (result.size() == 0) {
                            List<Integer> integers = Arrays.asList(nums[i], nums[j], nums[k]);
//                            integers.sort((o1, o2) -> o1 > o2 ? -1 : 1);
                            if (nums[i] .compareTo(nums[j])==0  && nums[j].compareTo(nums[k])==0 ) {
                                hasZore = true;
                            }
                            result.add(integers);
                        } else {
                            for (List list : result) {
                                if (list.contains(nums[i]) && list.contains(nums[j]) && list.contains(nums[k])
                                ) {
//                                    list.retainAll()
                                    needAdd = false;
                                    if (nums[i].compareTo(nums[j]) == 0 && nums[j].compareTo(nums[k]) == 0 && !hasZore) {
                                        needAdd = true;
                                    }
                                }
                            }
                            if (needAdd) {
                                if (nums[i].compareTo(nums[j]) == 0 && nums[j].compareTo(nums[k]) == 0) {
                                    hasZore = true;
                                }
                                result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                            }

                        }
//                        System.out.println(System.nanoTime() - l);
                    }
                }
            }
        }
        return result;
    }

}
