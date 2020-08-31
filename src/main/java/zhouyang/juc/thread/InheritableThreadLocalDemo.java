package zhouyang.juc.thread;

/**
 * 线程本地变量  这个可以保证线程变量的继承性子线程可以访问父线程的变量
 *
 * 其 精髓 就在于每一个thread 线程的内部都有一个ThreadLocalMap类型的 threadLocals
 * 变量 其中存储的就是当前线程对应的本地变量的key 和value
 *
 * @author dzd
 */
public class InheritableThreadLocalDemo {

    static ThreadLocal<String> localVariable=new ThreadLocal<>();
    //static ThreadLocal<String> localVariable=new InheritableThreadLocal<>();
    //打印当前线程的变量
    static void print(String str){
        System.out.println(str+": "+localVariable.get());
        localVariable.remove();
    }

    public static void main(String[] args) {
        localVariable.set("吃了么");
        Thread threadOne = new Thread(
                ()->{

                    print(Thread.currentThread().getName());
                    System.out.println("threadOne 移除之后"+localVariable.get());
                }
        ,"threadOne");


        threadOne.start();

    }
}
