package naixue.one;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ArrayListDemo {
    public static void main(String[] args) {
        Integer [] arr={2,3};
        //这个得到的arrayList 实际上不是我们正常的arrayList 虽然也是List的实现类
        //但只是一个静态内部类并且其数组的类型是根据传入参数的泛型而定的所以此处并不相等
        List<Integer> integers = Arrays.asList(arr);
        System.out.println(integers.toArray().getClass()==Object[].class);
        //然而此处的list内部的真正存储就是一个Object类型 所以是相等的
        List<Integer> aa= new ArrayList<>();
        System.out.println(aa.toArray().getClass()==Object[].class);
        aa.add(1);
        /**
         * 调用迭代器方法的时候都会初始化出来这个 迭代器的初始期望修改次数=list的修改次数
         *  int expectedModCount = modCount;
         */
        Iterator<Integer> iterator = aa.iterator();
        iterator.next();
        integers.add(11);



        /**
         * 这个构造方法有坑
         * 之所以判断了两个的类型是否相等
         *     public ArrayList(Collection < ? extends E > c){
         *elementData = c.toArray();
         *if ((size = elementData.length) != 0) {
         *             // c.toArray might (incorrectly) not return Object[] (see 6260652)
         *if (elementData.getClass() != Object[].class)
         *elementData = Arrays.copyOf(elementData, size, Object[].class);
         *} else {
         *             // replace with empty array.
         *this.elementData = EMPTY_ELEMENTDATA;
         *}
         *}
         */
    }
}
