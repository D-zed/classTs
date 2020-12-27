package naixue.two.vip.nefeatures.stream;

import com.sun.corba.se.impl.orbutil.CorbaResourceUtil;
import naixue.two.vip.nefeatures.lambda.Offer;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class StreamStop {

    Offer[] offerArray = {new Offer(101, "百度", 500, 9999.99, Offer.Level.ONE),
            new Offer(102, "阿里巴巴", 700, 6666.66, Offer.Level.TWO),
            new Offer(103, "腾讯", 800, 6666.66, Offer.Level.THREE),
            new Offer(104, "字节跳动", 400, 7777.77, Offer.Level.TWO),
            new Offer(105, "奈学教育", 1000, 5555.55, Offer.Level.ONE)};

    List<Offer> offerList = Arrays.asList(offerArray);

    @Test
    public void test01(){
       boolean b1 =  offerList.stream().noneMatch(o->o.getLevel().equals(Offer.Level.ONE));
        System.out.println(b1);

        Optional<Offer> op = offerList.parallelStream().filter(offer -> offer.getLevel().equals(Offer.Level.ONE)).findAny();
        System.out.println(op.get());

        Optional<Double> max = Arrays.stream(offerArray).map(Offer::getSalary).max(Double::compare);
        System.out.println(max.get());


    }

    @Test
    public void test03(){
        /**
         * 模拟大数据中wordCount的demo
         */
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        Optional<Double> opt = offerList.stream().map(Offer::getSalary).reduce(Double::sum);
        System.out.println(opt.get());

    }

    @Test
    public void test04(){
        List<String> list = offerList.stream().map(Offer::getComName).collect(Collectors.toList());
        System.out.println(list);

        HashSet<String> hashset = offerList.stream().map(offer -> offer.getComName()).collect(Collectors.toCollection(HashSet::new));
        System.out.println(hashset);

        Double avg = offerList.stream().collect(Collectors.averagingDouble(Offer::getSalary));
        System.out.println(avg);

        Map<Offer.Level, List<Offer>> map = offerList.stream().collect(Collectors.groupingBy(Offer::getLevel));
        System.out.println(map);
    }
}
