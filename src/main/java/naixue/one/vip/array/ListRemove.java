package naixue.one.vip.array;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * list的元素删除
 * @author dzd
 */
public class ListRemove {

    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(9);
        integers.add(10);
        integers.add(11);
        integers.add(12);
        integers.add(13);
        integers.add(14);
        integers.add(15);
        integers.add(16);
        integers.add(17);
        integers.add(18);
        integers.add(19);

        //此为传入一个断言的表达式 然后重新循环覆盖了一次数据做删除，并不是使用 system.copy
        integers.removeIf(integer -> integer > 15);
        System.out.println(JSONObject.toJSONString(integers));

        Iterator<Integer> iterator = integers.iterator();

        //这么写删除方法会报 java.util.ConcurrentModificationException 异常，因为使用迭代器的过程中会进行 期望操作数和操作次数的校验，如果此时删除使用的是
        //list自己的remove就会导致modcount大于 期望的操作次数，无法通过校验
        while (iterator.hasNext()){
            Integer next = iterator.next();
            if (next%2==0){
                integers.remove(next);
            }else {
                System.out.println(next);
            }

        }
    }


}
