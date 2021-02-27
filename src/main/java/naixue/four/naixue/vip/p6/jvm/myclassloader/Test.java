package naixue.four.naixue.vip.p6.jvm.myclassloader;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, IOException {
        MyClassLoader myClassLoader = new MyClassLoader("F:/myClassLoader");

        Class<?> aClass = myClassLoader.findClass("zhouyang.juc.thread.newthread.classloader.HelloWorld");

        Object o = aClass.newInstance();



    }
}
