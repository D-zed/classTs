package zhouyang.juc;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列
 * 底层是优先队列实现的 并且每个节点需要实现delayed 并且重写 compare方法
 *
 * @author dzd
 */
public class DelayQueueDemo {

    static class DelayedEle implements Delayed{

        private final  long delayTime; //延迟时间

        private final long expire; //过期时间

        private String taskName; //任务名称

        public DelayedEle(long delayTime, String taskName) {
            this.delayTime = delayTime;
            this.taskName = taskName;
            expire=System.currentTimeMillis()+delayTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            //获取剩余时间
            return unit.convert(this.expire-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int)(this.getDelay(TimeUnit.MILLISECONDS)-o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return "DelayedEle{" +
                    "delayTime=" + delayTime +
                    ", expire=" + expire +
                    ", taskName='" + taskName + '\'' +
                    '}';
        }
    }


    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedEle> delayQueue=new DelayQueue();

        Random random=new Random();

        for (int i = 0; i < 10; i++) {
            DelayedEle element=new DelayedEle(random.nextInt(500),"task:"+i);
            delayQueue.offer(element);
        }

        //依次取出并打印
        DelayedEle ele=null;

        for (;;){
            while ((ele=delayQueue.take())!=null){
                System.out.println(ele.toString());
            }
        }

        /**
         *
         * 233页
         * public E take() throws InterruptedException {
         *         final ReentrantLock lock = this.lock;
         *         lock.lockInterruptibly();
         *         try {
         *             for (;;) {
         *                 (1)获取但不移除队首元素
         *                 E first = q.peek();
         *                 if (first == null)
         *                     （2）
         *                     available.await();
         *                 else {
         *                     long delay = first.getDelay(NANOSECONDS);
         *                     （3）
         *                     if (delay <= 0)
         *                         return q.poll();
         *                     first = null; // don't retain ref while waiting
         *                     （4）
         *                     if (leader != null)
         *                         available.await();
         *                     else {
         *                         Thread thisThread = Thread.currentThread();
         *                         （5）
         *                         leader = thisThread;
         *                         try {
         *                            （6）
         *                             available.awaitNanos(delay);
         *                         } finally {
         *                             if (leader == thisThread)
         *                                 leader = null;
         *                         }
         *                     }
         *                 }
         *             }
         *         } finally {
         *             （7）
         *             if (leader == null && q.peek() != null)
         *                 available.signal();
         *             （8）
         *             lock.unlock();
         *         }
         *     }
         */

    }


}
