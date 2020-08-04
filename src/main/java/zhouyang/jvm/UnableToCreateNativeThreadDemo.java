package zhouyang.jvm;


/**
 * 不能创建线程异常
 * @author dzd
 */
public class UnableToCreateNativeThreadDemo {

    /**
     * 记一次异常排查
     * 线程与语言无关和操作系统有关
     * 你的应用创建了过多的线程
     * linux允许一个进程创建的线程数的上限是1024个 一般会小于这个数据，且如果是root用户的话则无上线
     * 解决方案要么降低 我们程序的线程数 要么提高系统的允许线程数
     */
    public static void main(String[] args) {

         Thread thread = new Thread(() -> {
        });
        //一个线程只能start一次
        thread.start();
      // thread.start();

        for (int i = 0; ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName());
            },Integer.toString(i)).start();
        }



    }
}
