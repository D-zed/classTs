package zhouyang.juc;

import java.util.concurrent.locks.LockSupport;

/**
 * lockSupport代表的是一个获取许可 和 消费许可的过程
 * 且许可只有 1和0两种情况  ，unpark许可加一 park许可减一  如果是0了就无法减一了 就阻塞了
 *
 * https://www.jianshu.com/p/1f16b838ccd8
 * @author dzd
 */
public class LockSupportDemo2 {


        public static void main(String[] args) throws InterruptedException {
            String a = new String("A");
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    long l = System.currentTimeMillis();
                    System.out.println("睡觉");
                    //如果首先unpark 也就是一上来就是加一
                    LockSupport.unpark(Thread.currentThread());
                    LockSupport.park(a);
                    System.out.println("起床 "+(System.currentTimeMillis()-l));
                }
            });
            t.setName("A-Name");
            t.start();
            Thread.sleep(5000);
            System.out.println("妈妈喊我起床");
            LockSupport.unpark(t);
        }
}
