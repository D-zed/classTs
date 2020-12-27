package naixue.two.vip.nefeatures.lambda;

import org.junit.Test;

import javax.lang.model.SourceVersion;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 函数式接口的抽象方法的入参和出参必须和实际的方法的入参和出参保持一致
 * 类名::静态方法
 * 对象::实例方法
 * 类名::实例方法
 * ----------------
 * 类名::new
 * type[]::new 数组引用
 */
public class MethodRefDemo {

    /**
     * 类名::方法名
     */
    @Test
    public void test01(){
        Consumer<String> consumer = str->System.out.println(str);
        consumer.accept("hello nx");

        Consumer<String> consumer1 = System.out::println;
        consumer1.accept("hello samuel");
    }

    @Test
    public void  test02(){
        Offer off = new Offer(101, "奈学", 100, 9999999);
        Supplier<String> sup = ()->off.getComName();
        System.out.println(sup.get());

        Supplier<String> sup1  = off::getComName;
        System.out.println(sup1.get());
    }

    /**
     * 方法的参数正好是实际调用的方法的调用者
     * 类名：：实例方法
     */
    @Test
    public void  test03(){
        BiPredicate<String,String> bp = (x,y)->x.equals(y);
        System.out.println(bp.test("abc","bcd"));
        BiPredicate<String,String> bp1 = String::equals;
        System.out.println(bp1.test("abc","bcd"));

        System.out.println("-------------------------");
        //需求，给定offer，得到offer的字符串表现形式，
        Function<Offer,String> fun = (o)->o.toString();
        System.out.println(fun.apply(new Offer(101, "奈学", 100, 9999999)));

        Function<Offer,String> fun2 = Offer::toString;
        System.out.println(fun2.apply(new Offer(101, "奈学", 100, 9999999)));

    }

    @Test
    public void test04(){
        Supplier<Offer> supplier = ()->new Offer();
        Supplier<Offer> supplier1 = Offer::new;
        System.out.println(supplier1.get());

        Function<Integer,Offer> fun1 = (id)->new Offer(id);
        Function<Integer,Offer> fun2 = Offer::new;

        Function<Integer,Offer[]> fun3 = Offer[]::new;
    }


}
