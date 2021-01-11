package zhouyang.jvm.exceptionerror;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

/**
 * 元空间
 * @author dzd
 */
public class MetaspaceDemo {

     static  class OomTest{

     }

    /**
     * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
     * 其中存储常量池 静态变量 class文件
     * @param args args
     */
    public static void main(String[] args) {

        int i=0;

        try{
            while (true){
                i++;
                //因为这个是字节码技术所以不断的将类加入元数据区
                Enhancer enhancer = new Enhancer();
                //不停的把静态内部类加入生成
                enhancer.setSuperclass(OomTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback((InvocationHandler) (o, method, objects) -> {
                    System.out.println("黑黑恶黑");
                    return null;
                });
                Object o = enhancer.create();

            }
        }catch (Throwable e){
            e.printStackTrace();
            System.out.println(i);
        }

    }
}
