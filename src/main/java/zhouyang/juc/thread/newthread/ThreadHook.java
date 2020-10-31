package zhouyang.juc.thread.newthread;

import java.util.concurrent.TimeUnit;

/**
 * jvm进程退出的时候 hook贤臣会启动执行
 * @author dzd
 */
public class ThreadHook {

    public static void main(String[] args) {
        //第一个钩子
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {

                try {
                    System.out.println("Thread hook thread is running");
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("hook end");
            }
        });

        //第二个钩子
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {

                try {
                    System.out.println("Thread hook thread 2 is running");
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("hook 2 end");
            }
        });
        while (true){

        }
    }
}
