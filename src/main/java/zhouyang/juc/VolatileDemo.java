package zhouyang.juc;

import java.util.concurrent.TimeUnit;


/**
 *
 * 可以解决内存的可见性 但是不能保证原子性
 * 原子性 可以类比mysql的事务 一个事务就是原子性的要不全部完成要不全部失败 ，不可拆分
 * 这个主要是因为jvm底层的多个指令完成一次读取造成的所以不能保证原子性，
 */
public class VolatileDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();

        Thread thread = new Thread(() -> {
            try {

                TimeUnit.SECONDS.sleep(2);
                System.out.println("我变成60了");
                myData.setNumber();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A");

        thread.start();

        while (myData.number==0){

        }
        //JMM是java内存模型并不是真是的
        //如果不加volatile 那么只会读取本线程的工作的内存不会读取主内存的值
        //加了volatile之后就会每次读取主内存
        //都是将从主内存拷贝过来使用跟新之后在写回去
        System.out.println("main thread over");

    }
}
class MyData{

   volatile int number=0;

    public void setNumber(){
        number=60;

    }
}