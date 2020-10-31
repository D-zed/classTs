package zhouyang.juc.thread.newthread;

import java.util.concurrent.TimeUnit;

/**
 * 线程的异常的捕获
 * @author dzd
 */
public class caughtExceptionDemo {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t,e)->{
            System.out.println(t.getName()+"occur exception");
            e.printStackTrace();
        });
       final Thread thread= new Thread(()->{

           try {
               TimeUnit.SECONDS.sleep(2);
           }catch (Exception e){

           }
           System.out.println(1/0);
        },"test-thread");
       thread.start();
    }
}
