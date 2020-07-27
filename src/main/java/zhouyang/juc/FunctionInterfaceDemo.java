package zhouyang.juc;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionInterfaceDemo {

    public static void main(String[] args) {
        Supplier<String> dd = () -> {
            return "我是生产函数式接口";
        };
        System.out.println(dd.get());

        Consumer<String> ddd = item -> {
            System.out.println("我是" + item + "函数式接口");
        };
        ddd.accept("消费");

        Function<String, String> function = item -> {
            return "我是" + item + "函数式接口";
        };
        System.out.println(function.apply("普通"));

        Predicate<String> pppp = item -> {
            System.out.println("我是" + item + "函数式接口");
            return true;
        };
        pppp.test("断言");
    }
}
