package naixue.two.stream;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://zhuanlan.zhihu.com/p/70450664
 * @author dzd
 */
public class TestStream {

    static void wordCount() {
        //1. 模拟数据源 两行数据
        List<String> text = Arrays.asList("hello java", "hello python");

       //2 将数据源碾碎，重新构建一个流
        Stream<String> stringStream = text.stream().flatMap(w->Stream.of(w.split(" ")));
        //3 对新的流进行计算
        Map<String, Long> result = stringStream.collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        //4 输出结果
        System.out.println(result);
    }

    public static void main(String[] args) {
        wordCount();
    }


}
