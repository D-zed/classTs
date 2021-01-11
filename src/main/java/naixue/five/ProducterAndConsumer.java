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
public class ProducterAndConsumer {
    private static int SIZE = 1024*1024*10;

    private static int top=50;
    private static int bottom=10;

    /**
     * 产品数量
     */
    private int num;

    private LinkedBlockingQueue<byte[]> product = new LinkedBlockingQueue<>();
    /**
     * 锁
     */
    private final ReentrantLock lock = new ReentrantLock();

    Condition condition = lock.newCondition();


    /**
     * 生产方法
     * 速率
     */
    public void product(Integer rate) {

        lock.lock();
        try {
            while (num > top) {
                System.out.println("当前产品数为：" + num + "停止生产");
                condition.await();
            }
           // TimeUnit.MILLISECONDS.sleep(rate);
            num++;
            byte[] bytes = new byte[SIZE];
            product.offer(bytes);
            System.out.println("生产 当前num：" + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


    /**
     * 生产方法
     * 速率
     */
    public void consume(Integer rate) {

        lock.lock();
        try {
            while (num < bottom) {
                System.out.println("当前产品数为：" + num + "停止消费");
                condition.await();
            }

           // TimeUnit.MILLISECONDS.sleep(rate);
            num--;
            product.poll();
            System.out.println("消费 当前num：" + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
