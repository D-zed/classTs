package zhouyang.juc.thread.newthread.singleton;

/**
 * holder 模式加枚举模式完成单例  在枚举的构造中进行赋值 枚举自动就是单例 还有效避免反射
 * @author dzd
 */
public class SingletonEnumHolder {

    //实例变量
    private byte [] data=new byte[1024];

    private SingletonEnumHolder(){

    }

    private enum EnumHolder{
        INSTANCE;

        private SingletonEnumHolder instance;
        EnumHolder(){
            System.out.println("初始化 EnumHolder");
            this.instance=new SingletonEnumHolder();
        }
        private SingletonEnumHolder getSingletonEnumHolder(){
            return instance;
        }
    }

    public static SingletonEnumHolder getInstance(){
        return EnumHolder.INSTANCE.getSingletonEnumHolder();
    }

}
