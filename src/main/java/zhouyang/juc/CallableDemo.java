package zhouyang.juc;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {

    //callable该怎么好用
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread2 myThread2=new MyThread2("thread的线程 ");
        myThread2.start();

        //这个是适配器模式的
        FutureTask<Integer> integerFutureTask = new FutureTask<Integer>(new MyThread());
        new Thread(integerFutureTask,"A").start();
        Integer integer = integerFutureTask.get();
        System.out.println(integer);
    }
}

//callable和thread表面上看起来没有任何关系，但是适配器模式FutrueTask类将Callable和Thread结合
class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {

        System.out.println(Thread.currentThread().getName());

        return 111;
    }
}

class MyThread2 extends Thread{

    public MyThread2(String name) {
        super(name);
    }

    public void get(){
        System.out.println(Thread.currentThread().getName()+"eeeeeeeeeeeeeeeeeeeeeeeeeeee");
    }

    @Override
    public void run() {

        get();
    }
}
