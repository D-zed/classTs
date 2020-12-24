package naixue.one.vip.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ArrayListDemo {

    private static List list = new ArrayList();

    /**
     * failFast 机制
     * 创建迭代器对象时 将全局的modCount赋值给迭代器的局部变量expectedModCount
     * 在迭代的过程中modCount！=expectedModCount快速抛出异常
     * @param args
     */
    public static void main(String[] args) {
        List list = new ArrayList<>();
//        for (int i=1;i<=11;i++){
//            list.add(i);
//            System.out.println(list.size());
//        }




        LinkedList<Object> linkedList = new LinkedList<>();
        for(int i=1;i<=11;i++){
            linkedList.add(i);
        }

        Collections.synchronizedList(list);
        new ThreadIterator(list).start();
        new ThreadAdd(list).start();
    }
}
