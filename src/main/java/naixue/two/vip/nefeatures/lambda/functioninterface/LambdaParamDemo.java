package naixue.two.vip.nefeatures.lambda.functioninterface;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * lambda作为方法的参数
 * lambda本质是一个函数式接口的 隐式实现的对象
 *
 * 定义了内置的四大函数式接口
 */
public class LambdaParamDemo {

    public String opStr(MyFunc<String> mf, String str){
        return mf.getValue(str);
    }

    @Test
    public void test01(){
        String newStr = opStr((str) -> str.toUpperCase(), "hello nx samuel");
        System.out.println(newStr);

        String news = opStr(str -> str.replace("h", "M"), "hello nx samuel");
        System.out.println(news);
    }
}
