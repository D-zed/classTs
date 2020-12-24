package naixue.one.vip.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListC {

    public static void main(String[] args) {
        Integer[] array = {1,2};
        List<Integer> integerList = Arrays.asList(array);
        Object[] objectArray = integerList.toArray();
        System.out.println(objectArray.getClass()==Object[].class); //true

        List<Integer> list = new ArrayList<>();
        System.out.println(list.toArray().getClass()==Object[].class);// 1\0



    }
}
