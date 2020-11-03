package zhouyang.juc.thread.newthread.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 使用类加载器加载并不会执行静态代码块 因为其只触发了 类加载部分的 加载阶段 没有执行到初始化
 * @author dzd
 */
public class MyClassLoaderTest6 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ClassLoader classLoader=null;
        //将父加载器置为null,进而破坏双亲委派 或者 将父加载器直接指定为扩展类加载器也可
        //这个仅仅重写了findClass
       // MyClassLoader5 myClassLoader = new MyClassLoader5(classLoader);

        //这个可以优先使用自定义的类加载器，如果不行才走父类
        BrokerDelegateClassLoader7 myClassLoader=new BrokerDelegateClassLoader7();

        //类加载的双亲委派的逻辑都在此 loadClass 其中进行了父类加载器的判断
        Class<?> aClass = myClassLoader.loadClass("zhouyang.juc.thread.newthread.classloader.HelloWorld");
        System.out.println("这个是什么 ："+aClass.getClassLoader());

        Object o = aClass.newInstance();

        System.out.println(o);
        Method welcome = aClass.getMethod("welcome");
        String invoke = (String)welcome.invoke(o);
        System.out.println("welcome 方法结果:"+invoke);
    }
}