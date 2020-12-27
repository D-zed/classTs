
package naixue.two.vip.nefeatures.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * lambda 本质就是一个函数式接口的隐式的实现(匿名实现)，对象
 * 函数式接口：：只包含了一个抽象方法的接口。@FunctionInterface
 */
public class OfferDemo {
    public static void main(String[] args) {
        //工资给的高的优先选择
        Comparator<Offer> comparator = new Comparator<Offer>() {

            @Override
            public int compare(Offer o1, Offer o2) {
                return (int) (o1.getSalary() - o2.getSalary());
            }
        };

        //lamdda
        Comparator<Offer> offerComparator = Comparator.comparingDouble(Offer::getSalary);
    }

    /**
     * 字符串的比较器，String 长度达的大
     * lambda可以自动类型推断
     */

    public void test01(){

        Comparator<String> comparator = (x,y) -> Integer.compare(x.length(), y.length());

        String[] strArr = {"hello","nx","samuel"};
        List<String> list = new ArrayList<>();

    }
}
