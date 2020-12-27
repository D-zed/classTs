package naixue.two.vip.nefeatures.lambda;

import java.util.function.Consumer;

public class LambdaCore {
    public static void main(String[] args) {
        Consumer<Offer> consumer = offer -> System.out.println("我收到了"+offer.getComName()+"的offer了");
        consumer.accept(new Offer(101, "奈学", 100, 9999999));

        //脱糖
        System.out.println(consumer.getClass().getName());

    }
}
