package naixue.three.vip.samuel.reflection.basic;

public class SingleDemo {

    private static SingleDemo instance; //

    private SingleDemo(){
        if(instance!=null){
            throw new RuntimeException("这个是单例，不允许重复创建对象");
        }

    }

    public static  SingleDemo getInstance(){
        if(instance == null){
            instance = new SingleDemo();
        }
        return instance;
    }
}
