package zhouyang.juc.thread;

/**
 * 线程本地变量
 *
 * 其 精髓 就在于每一个thread 线程的内部都有一个ThreadLocalMap类型的 threadLocals
 * 变量 其中存储的就是当前线程对应的本地变量的key 和value
 * @author dzd
 */
public class ThreadLocalDemo {

    static ThreadLocal<String> localVariable=new ThreadLocal<>();
    //打印当前线程的变量
    static void print(String str){
        System.out.println(str+": "+localVariable.get());
        localVariable.remove();
    }

    public static void main(String[] args) {
        Thread threadOne = new Thread(
                ()->{
                    localVariable.set("threadOne local variable");
                    print(Thread.currentThread().getName());
                    System.out.println("threadOne 移除之后"+localVariable.get());
                }
        ,"threadOne");

        Thread threadTwo = new Thread(
                () -> {
                    localVariable.set("threadTwo local variable");
                    print(Thread.currentThread().getName());
                    System.out.println("threadTwo 移除之后"+localVariable.get());
                }
        ,"threadTwo");

        threadOne.start();
        threadTwo.start();
    }
}
