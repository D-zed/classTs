package naixue.three.vip.samuel.reflection.basic;

import java.lang.reflect.Constructor;

public class SingleMain {

    public static void main(String[] args) throws Exception {
        SingleDemo instance1 = SingleDemo.getInstance();
        SingleDemo instance2 = SingleDemo.getInstance();
        System.out.println(instance1==instance2);


        Constructor<? extends SingleDemo> constructor = instance1.getClass().getDeclaredConstructor();
        constructor.setAccessible(true);
        SingleDemo instance3 = constructor.newInstance();
        System.out.println(instance1==instance3);


    }
}
