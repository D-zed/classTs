
package naixue.three.vip.samuel.generic.genuse;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * 堆污染：指的是当把一个不带泛型的对象赋值给一个带泛型的变量时可能发生的一种想象
 * 通俗讲就是把一个不带泛型的对象赋值给一个带有泛型的对象编译期间不会报错，但是运行的时候还是逃不掉泛型检查，可能会出现问题
 * 型变
 *   协变 covariance
 *   逆变。。。
 *   不变
 *   A、B类型
 *   f()类型转换
 *   <=表示的继承
 *   A <=B
 *
 *   1、A《=B 时 f(A)<=f(B) 那么f()是协变
 *   2、A《=B 时 f(B)<=f(A) 那么f()是逆变
 *   3、不变
 *
 *   作业：：Java中不支持泛型数组，实现一个支持泛型的数组【反射结合】
 */
public class Pollution {


    public static void main(String[] args) {
        Set set = new TreeSet<>();
        set.add("samuel");
        Set<Integer> hashSet=new HashSet<>();
        hashSet=set;
        hashSet.add(1);
        genMethod(set);
        Iterator iterator = set.stream().iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            System.out.println(obj);
        }

    }

    public static void  genMethod(Set<Integer> set){
        set.add(new Integer(100));
        System.out.println(set.size());
    }


}
