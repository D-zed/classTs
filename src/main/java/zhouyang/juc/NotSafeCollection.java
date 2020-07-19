package zhouyang.juc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class NotSafeCollection {


    //java.util.ConcurrentModificationException
   /* public static void main(String[] args) {
        //用这个写时复制其实内部也是用的lock控制的只不过在写之前先拷贝一份在美写完的时候读的还是原来的那份，写的时候加锁
        //相当于读写分离的思想
        //CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
*/
    /*public static void main(String[] args) {
        //用这个写时复制其实内部也是用的lock控制的只不过在写之前先拷贝一份在美写完的时候读的还是原来的那份，写的时候加锁
        //相当于读写分离的思想
        //HashSet<String> set = new HashSet<>();
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 300; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
*/

    public static void main(String[] args) {
        //用这个写时复制其实内部也是用的lock控制的只不过在写之前先拷贝一份在美写完的时候读的还是原来的那份，写的时候加锁
        //相当于读写分离的思想
        //HashSet<String> set = new HashSet<>();
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 300; i++) {
            new Thread(()->{
                String substring = UUID.randomUUID().toString().substring(0, 8);
                map.put(substring,substring);
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
