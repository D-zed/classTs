package zhouyang.juc;

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockQueueDemo {
    public static void main(String[] args) throws InterruptedException {

        Ts c = new Ts("吃了么");
        Node<Ts> first = new Node<>(c);
        Ts ts= first.item;
        System.out.println(ts.toString());
        first.item=null;

        System.out.println(ts.toString());

        LinkedBlockingQueue<String> objects = new LinkedBlockingQueue<>(1);
        //根据源码看出 这个插入如果大于capacity则直接绕过逻辑 不会报错
        objects.offer("ss");

        //双重加锁
        objects.remove("ss");
        objects.offer("sds");
        //这个由于是阻塞的queue所以其size 相比 concurrentQueue准确
        objects.size();

        System.out.println(objects);
        //这个用的锁是 lockInterruptibly 也即是中断会显示爆出异常的 且如果满了会直接阻塞到那里
        objects.put("999");

        //删除并且返回
        objects.poll();
        //删除并返回 理解为消费 如果队列是空的则 wait等待生产者唤醒
        objects.take();

        /**
         *
         * 这个使用的是生产消费模式
         *  public boolean offer(E e) {
         *         （1）校验空指针
         *         if (e == null) throw new NullPointerException();
         *         final AtomicInteger count = this.count;
         *         //（2）校验队列容量
         *         if (count.get() == capacity)
         *             return false;
         *          （3）构造新节点 获取putlock独占锁
         *         int c = -1;
         *         Node<E> node = new Node<E>(e);
         *         final ReentrantLock putLock = this.putLock;
         *         putLock.lock();
         *         try {
         *             （4）如果队列不满则进队列，并递增元素计数
         *             if (count.get() < capacity) {
         *                 enqueue(node);
         *                 c = count.getAndIncrement();
         *                 （5）
         *                 if (c + 1 < capacity)
         *                     notFull.signal();
         *             }
         *         } finally {
         *            （6）
         *             putLock.unlock();
         *         }
         *         （7）
         *         if (c == 0)
         *             signalNotEmpty();
         *        （8）
         *         return c >= 0;
         *     }
         *
         *       private void enqueue (Node < E > node) {
         *         // assert putLock.isHeldByCurrentThread();
         *         // assert last.next == null;
         *         last = last.next = node;
         *       }
         */

        /**
         *  public void put(E e) throws InterruptedException {
         *         if (e == null) throw new NullPointerException();
         *         // Note: convention in all put/take/etc is to preset local var
         *         // holding count negative to indicate failure unless set.
         *         int c = -1;
         *         Node<E> node = new Node<E>(e);
         *         final ReentrantLock putLock = this.putLock;
         *         final AtomicInteger count = this.count;
         *         putLock.lockInterruptibly();
         *         try {
                         *while (count.get() == capacity) {
                         *notFull.await();
                         *}
                         *enqueue(node);
                         *c = count.getAndIncrement();
                         *if (c + 1 < capacity)
                         *notFull.signal();
                         *} finally

                    {
                         *putLock.unlock();
                         *}
         * //此时说明至少有一个元素 所以可以唤醒消费线程消费了 加之前是0个元素
                         *if(c ==0)
                            *

                    signalNotEmpty();
                         *
                }
                         */


        /**
         *
         *  public E poll() {
         *         final AtomicInteger count = this.count;
         *         if (count.get() == 0)
         *             return null;
         *         E x = null;
         *         int c = -1;
         *         final ReentrantLock takeLock = this.takeLock;
         *         takeLock.lock();
         *         try {
         *             if (count.get() > 0) {
         *                 x = dequeue();
         *                 c = count.getAndDecrement();
         *                 if (c > 1)
         *                     notEmpty.signal();
         *             }
         *         } finally {
         *             takeLock.unlock();
         *         }
         *         if (c == capacity)
         *             signalNotFull();
         *         return x;
         *     }
         *      private E dequeue() {
         *        //获得head的引用
         *         Node<E> h = head;
         *         //重新指定 first
         *         Node<E> first = h.next;
         *         //head自引用  因为head为哨兵节点 也就是虚拟节点
         *         h.next = h; // help GC
         *         新的head 是当前的first
         *         head = first;
         *         然后返回 x
         *         E x = first.item;
         *         将其指向null成为新的哨兵节点
         *         first.item = null;
         *         return x;
         *     }
         *
         */
    }

    public static class Node<E>{

        E item;

        public Node(E item){
          this.item=item;
        }
    }

}
class Ts{
    private String name;

    public Ts(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return "Ts{" +
                "name='" + name + '\'' +
                '}';
    }
}
