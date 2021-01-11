package naixue.five;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者
 *
 * @author dzd
 */
public class ProducterAndConsumer2 {
    private static int SIZE = 1024*1024*10;

    private static int top=50;
    private static int bottom=10;

    /**
     * 产品数量
     */
    private int num;

    private LinkedBlockingQueue<byte[]> product = new LinkedBlockingQueue<>();



    /**
     * 生产方法
     * 速率
     */
    public  void product(Integer rate) {
       synchronized (this){
           try {
               while (num > top) {
                   System.out.println("当前产品数为：" + num + "停止生产");
                   wait();
               }
               //TimeUnit.MILLISECONDS.sleep(rate);
               num++;
               byte[] bytes = new byte[SIZE];
               product.offer(bytes);
               System.out.println("生产 当前num：" + num);
               notifyAll();
           } catch (Exception e) {
               e.printStackTrace();
           } finally {
           }
       }

    }


    /**
     * 生产方法
     * 速率
     */
    public  void consume(Integer rate) {

        synchronized (this){
            try {
                while (num < bottom) {
                    System.out.println("当前产品数为：" + num + "停止消费");
                    wait();
                }
                //TimeUnit.MILLISECONDS.sleep(rate);
                num--;
                product.poll();
                System.out.println("消费 当前num：" + num);
                notifyAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }
    }
}
