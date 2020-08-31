package zhouyang.juc.thread;

public class JoinDemo2 {

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {

            try {
                Thread.sleep(5000);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("child threadOne over");
        }, "threadOne");

      final   Thread mainThread = Thread.currentThread();
        Thread threadTwo = new Thread(() -> {

            try {
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("child threadTwo over");
            //中断使得主线程直接报错
            mainThread.interrupt();
        }, "threadTwo");


        threadOne.start();
        threadTwo.start();
        System.out.println("wait all child thread over");

        try {
            threadOne.join();
            System.out.println("threadTwo ff");
            threadTwo.join();
            System.out.println (" all ch ld thread over") ;
        }catch (Exception e){
            System.out.println("是我");
            e.printStackTrace();
        }

    }
}
