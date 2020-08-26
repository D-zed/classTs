package zhouyang.juc;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 为了线程调度，在许可可用之前阻塞当前线程。
 * 如果许可可用，则使用该许可，并且该调用立即返回；
 * 否则，为线程调度禁用当前线程，
 * 如果对park的线程  unpark可以解除休眠状态 ，以下三种情况都可以解除休眠
 * 1. 其他某个线程将当前线程作为目标调用 unpark
 * 2. 其他某个线程中断当前线程
 * 3. 该调用不合逻辑地（即毫无理由地）返回
 * <p>
 * lockSupport代表的是一个获取许可 和 消费许可的过程
 * 且许可只有 1和0两种情况  ，unpark许可加一 park许可减一  如果是0了就无法减一了 就阻塞了
 * <p>
 * 线程中断使得解除休眠
 * interrupt 是设置线程为中断状态 如果是阻塞中的线程 会抛出异常 InterruptedException
 * interrupted 判断当前线程是否中断 清除中断状态
 * isInterrupted 判断当前线程是否中断 不清除中断状态
 * https://www.jianshu.com/p/1f16b838ccd8
 *
 * @author dzd
 */
public class LockSupportDemo1 {


    @Test
    public void test() {

        String a = new String("a");
        Thread t = new Thread(() -> {
            System.out.println("睡觉");
             //LockSupport.park(a);
              /*  try {
                    TimeUnit.SECONDS.sleep(88);
                } catch (InterruptedException e) {

                }*/

            /*
            使用wait这块没和notify共用造成了异常
            WaitDemo waitDemo=new WaitDemo();
            waitDemo.wait0(this);*/

            System.out.println("起床");
            System.out.println("是否中断 " + Thread.currentThread().isInterrupted());
        }, "A-name");
        t.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();
        System.out.println("肚子突然很疼");
    }
}

class WaitDemo{
    public void wait0(Object a){
        synchronized (a) {
            try {
                this.wait(80000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
