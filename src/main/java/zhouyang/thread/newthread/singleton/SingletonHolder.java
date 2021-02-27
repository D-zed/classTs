package zhouyang.thread.newthread.singleton;


/**
 * 类被加载的时候内部类不会被加载 只有在被使用的时候才会  比如调用了静态属性
 * @author dzd
 */
public final class SingletonHolder {
    private byte[] data=new byte[1024];
    static {
        System.out.println("SingletonHolder 初始化");
    }
    public SingletonHolder(){

    }

    private static class Holder
    {
        private static SingletonHolder instance=new SingletonHolder();
        static {
            System.out.println("holder 初始化"+instance);
        }
    }

    public static SingletonHolder getInstance(){
        return Holder.instance;
    }

}
