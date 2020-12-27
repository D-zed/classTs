package naixue.two.vip.nefeatures.stream;

import naixue.two.vip.nefeatures.lambda.Offer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamOptDemo {

    Offer[] offerArray = {new Offer(101, "百度", 500, 9999.99),
            new Offer(102, "阿里巴巴", 700, 6666.66),
            new Offer(103, "腾讯", 800, 6666.66),
            new Offer(104, "字节跳动", 400, 7777.77),
            new Offer(105, "奈学教育", 1000, 5555.55)};

    List<Offer> offerList = Arrays.asList(offerArray);

    @Test
    public void test01() {
        Stream<Offer> stream = offerList.stream().filter(offer -> {
            System.out.println("filter过滤");
            return offer.getSalary() >= 6666;
        }).limit(2).skip(1).distinct();

        //终止
        stream.forEach(System.out::println);
    }

    /**
     * 映射
     * 将所有的offer中的公司名提出出来
     */
    @Test
    public void test02() {

        Stream<String> stream = offerList.stream().map(o -> o.getComName());
        stream.forEach(System.out::println);
        System.out.println("-----------------------------------");

        List<String> stringList = Arrays.asList("bbb", "ddd", "eee", "rrr");
        stringList.stream().map(String::toUpperCase).forEach(System.out::println);

        System.out.println("-----------------------------------");

        //提出每个String中的char形成一个新流
        Stream<Stream<Character>> streamStream = stringList.stream().map(str -> {
            List<Character> list = new ArrayList<>();
            for (Character ch : str.toCharArray()) {
                list.add(ch);
            }
            return list.stream();
        });
        //得到流中的数据
        streamStream.forEach(sm -> {
            sm.forEach(System.out::println);
        });
        System.out.println("-----------------------------------");
        //提出每个String中的char形成一个新流
        Stream<Character> characterStream = stringList.stream().flatMap(str -> {
            List<Character> list = new ArrayList<>();
            for (Character ch : str.toCharArray()) {
                list.add(ch);
            }
            return list.stream();
        });
        characterStream.forEach(System.out::println);

    }

    @Test
    public void test03() {
        List<String> stringList = Arrays.asList("bbb", "ddd", "eee", "rrr");
        stringList.stream().sorted().forEach(System.out::println);
        System.out.println("--------------");
        offerList.stream().sorted((o1, o2) -> o1.getId() - o2.getId()).forEach(System.out::println);
    }
}
