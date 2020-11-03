package zhouyang.juc.thread.newthread.singleton;

/**
 * 用枚举实现单例
 * @author dzd
 */
public enum  SingletonEnum {

    INSTANCE;
    String name;


    SingletonEnum(){
        System.out.println("INSTANCE 初始化");
    }

    public static void method(){

    }

    public static SingletonEnum getInstance(){
        return INSTANCE;
    }

}
