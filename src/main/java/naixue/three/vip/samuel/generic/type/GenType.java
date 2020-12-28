package naixue.three.vip.samuel.generic.type;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 泛型不影响实际的数据类型
 * 泛型只在编译阶段期作用，在编译之后程序会采取去泛型化的处理
 */
public class GenType {

    @Test
    public void m01(){
        List list = new ArrayList<>();
        List<String> strList = new ArrayList<>();

        System.out.println(list.getClass());
        System.out.println(strList.getClass());
        System.out.println(list.getClass()==strList.getClass()); //true false
    }


    @Test
    public void  method02() throws Exception{
        List<String> stringList = new ArrayList<>(); //类型推断、1.7
        stringList.add("nx");
        stringList.add("samuel");
        System.out.println(stringList.size());//2
        System.out.println("-----------------------------------------");
        Class<? extends List> clazz = stringList.getClass();
        Method addM = clazz.getDeclaredMethod("add", Object.class);
        addM.invoke(stringList,new Object()); //stringList.add(new Object);
        System.out.println(stringList.size());//3


    }




}
