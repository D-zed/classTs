package zhouyang.thread;

/**
 * join代表将线程加入到当前线程 也就是说要执行完加入的线程才能继续当前线程
 * @author ASUS
 */
public class JoinDemo {

    public static void main(String[] args) throws InterruptedException {

        Thread threadOne = new Thread(() -> {

            try {
                Thread.sleep(10000);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("child threadOne over");
        }, "threadOne");


        Thread threadTwo = new Thread(() -> {

            try {
                Thread.sleep(5000);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("child threadTwo over");
        }, "threadTwo");


        threadOne.start();
        threadTwo.start();
        System.out.println("wait all child thread over");

        //两个线程加入之后一定是都执行完了才会继续 如果one 时间长 则会阻塞在这里  如果one时间短 则阻塞在two
        threadOne.join();

        System.out.println("threadTwo ff");
        threadTwo.join();
        System.out.println (" all ch ld thread over") ;
    }
}
