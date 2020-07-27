package zhouyang.juc;

public class VolatileDemo4 {

    //volatile对dcl的优化，禁止指令重排的重要性

    public static void main(String[] args) {

        for (int i = 0; i < 1000000; i++) {
           new Thread(()->{
               SingleDemo.getInstance();
           },Integer.toString(i)).start();
        }
    }

}
class SingleDemo{

    private static volatile SingleDemo instance=null;

    private SingleDemo(){
        System.out.println("我是单例我只能出现一次");
    }

    /**
     * new SingleDemo的时候可能会发生指令重排
     * 1分配对象空间
     * 2初始化对象
     * 3指向对象所在内存空间
     * 2，3步骤可能会调换顺序，也即是后续线程过来的时候判断认为已经初始化了
     * 但是可能是一个没有初始化完全的对象也即返回null
     *
     * 这个时候添加一个volatile防止其指令重排
     * @return
     */
    public static SingleDemo getInstance(){
        if (instance==null){
            synchronized (SingleDemo.class){
                if (instance==null){
                    instance=new SingleDemo();
                }
            }
        }
        return instance;
    }
}
