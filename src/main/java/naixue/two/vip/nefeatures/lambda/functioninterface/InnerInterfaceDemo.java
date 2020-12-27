package naixue.two.vip.nefeatures.lambda.functioninterface;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 四大接口
 */
public class InnerInterfaceDemo {

    /**
     * 消费性接口 consumer 传T类型参数
     */
    @Test
    public void test01(){
        omgBuy(1000,m-> System.out.println("omg 买它买它.."+m+"元"));
    }
    public void omgBuy(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }

    /**
     * 创建型接口 supplier<T>
     * T get();
     */
    public List<Object> newObjSelf(int num, Supplier<Object> sup){
        List<Object> grList = new ArrayList<>();
        for (int i=0;i<=num;i++){
            Object o = sup.get();
            grList.add(o);
        }
        return grList;
    }
    public void test02(){
        List<Object> objects = newObjSelf(7, () -> new Object());
    }

    /**
     * 编号处理
     * 函数型接口 Function
     */
    public List<String> grHandler(List<String> strList, Function<List<String>,List<String>> fun){
        return fun.apply(strList);
    }

    @Test
    public void test03(){
        List<String> grList = Arrays.asList("阿珂","建宁公主","苏全","教主","双儿");
        List<String> newList = grHandler(grList,(wf)->{
            List list = new ArrayList();
            for(int i=0;i<wf.size();i++){
                String name = wf.get(i);
                name = name+"星期："+(i+1);
                list.add(name);
            }
            return list;
        });
        System.out.println(newList);
    }

    //将满足条件的String，放到新集合中，1,3,5
    public List<String> filterStr(List<String> list, Predicate<String> pre){
        List newlist = new ArrayList();
        for (String str:list){
            if(pre.test(str)){
                newlist.add(str);
            }
        }
        return newlist;
    }

    @Test
    public void test04(){
        List<String> grList = Arrays.asList("阿珂1","建宁公主2","苏全3","教主4","双儿5");
        filterStr(grList,(wife)->{
            int num = Integer.parseInt(wife.substring(wife.length() - 1));
            if (num==1||num==3||num==5) return true;
            return false;
        });
    }
}
