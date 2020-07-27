package zhouyang.juc;

public class SyncronizedAndLock {
    /**
     * syncronized是jvm层面的锁 他是java关键字 底层是基于jvm指令的
     *
     * lock是一个java5以后的类，是api层面的，底层基于aqs和cas
     */

    //配置javap -c https://blog.csdn.net/weixin_30409927/article/details/102951048
    public static void main(String[] args) {
        synchronized (Object.class){

        }
    }
}
