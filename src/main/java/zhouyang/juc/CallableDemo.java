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
        //并且要注意我们的futuretask实现了runable所以他才是真正的线程逻辑 如果多个thread传入了一个 futureTask是没用的，那也只能是一个线程
        FutureTask<Integer> integerFutureTask = new FutureTask<>(new MyThread());
        FutureTask<Integer> integerFutureTask2 = new FutureTask<>(new MyThread());
        new Thread(integerFutureTask,"A").start();
        new Thread(integerFutureTask,"B").start();

        System.out.println("先做简单题");
        Integer integer = integerFutureTask.get();
        Integer integer1 = integerFutureTask2.get();
        System.out.println("在做难题"+integer+"----"+integer1);
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
