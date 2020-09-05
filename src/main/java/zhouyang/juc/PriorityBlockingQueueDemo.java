package zhouyang.juc;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 218页
 * 底层使用平衡二叉堆实现
 * @author dzd
 */
public class PriorityBlockingQueueDemo {

    public static void main(String[] args) {
        PriorityBlockingQueue priorityBlockingQueue=new PriorityBlockingQueue();

        priorityBlockingQueue.offer("hhhhh");

        priorityBlockingQueue.poll();


        //其size也是一个准确的
        priorityBlockingQueue.size();
        //这个是一个可以自己扩容的队列

        /**
         * 初始容量是11
         *  public boolean offer(E e) {
         *         if (e == null)
         *             throw new NullPointerException();
         *         final ReentrantLock lock = this.lock;
         *         lock.lock();
         *         int n, cap;
         *         Object[] array;
         *         (1)如果当前元素个数>=队列容量则扩容
         *         while ((n = size) >= (cap = (array = queue).length))
         *             tryGrow(array, cap);
         *         try {
         *            （2）
         *             Comparator<? super E> cmp = comparator;
         *             if (cmp == null)
         *                 siftUpComparable(n, e, array);
         *             else
         *                （3）
         *                 siftUpUsingComparator(n, e, array, cmp);
         *             （9）
         *             size = n + 1;
         *             notEmpty.signal();
         *         } finally {
         *             lock.unlock();
         *         }
         *         return true;
         *     }
         *
         *      private void tryGrow(Object[] array, int oldCap) {
         *      //首先在扩容的时候释放锁不予以阻塞
         *         lock.unlock();
         *         Object[] newArray = null;
         *
         *         //（4）cas成功则扩容  allocationSpinLock 为0表示当前数组没有在扩容 为1的时候代表正在扩容  volatile
         *         //如果没在扩容则cas使其扩容
         *         if (allocationSpinLock == 0 &&
         *             UNSAFE.compareAndSwapInt(this, allocationSpinLockOffset,
         *                                      0, 1)) {
         *             try {
         *                 int newCap = oldCap + ((oldCap < 64) ?
         *                                        (oldCap + 2) : // grow faster if small
         *                                        (oldCap >> 1));
         *                 if (newCap - MAX_ARRAY_SIZE > 0) {    // possible overflow
         *                     int minCap = oldCap + 1;
         *                     if (minCap < 0 || minCap > MAX_ARRAY_SIZE)
         *                         throw new OutOfMemoryError();
         *                     newCap = MAX_ARRAY_SIZE;
         *                 }
         *                 if (newCap > oldCap && queue == array)
         *                     newArray = new Object[newCap];
         *             } finally {
         *                 allocationSpinLock = 0;
         *             }
         *         }
         *         //（5）第一个线程cas成功后
         *         //如果目前有线程在扩容 则礼让一次 ，然而这并不代表一定可以礼让成功
         *         if (newArray == null) // back off if another thread is allocating
         *             Thread.yield();
         *         // （6） 如果线程礼让没有达到效果反而再次获取锁，那么就进入代码 1进行判断 再次进入扩容
         *         lock.lock();
         *         if (newArray != null && queue == array) {
         *             queue = newArray;
         *             System.arraycopy(array, 0, newArray, 0, oldCap);
         *         }
         *     }
         *
         *
         *     private static <T> void siftUpComparable(int k, T x, Object[] array) {
         *         Comparable<? super T> key = (Comparable<? super T>) x;
         *         //（7）
         *         //堆的算法 由此节点的位置依次与父节点比较然后交换插入
         *         while (k > 0) {
         *             int parent = (k - 1) >>> 1;
         *             Object e = array[parent];
         *             if (key.compareTo((T) e) >= 0)
         *                 break;
         *             array[k] = e;
         *             k = parent;
         *         }
         *         //（8）
         *         array[k] = key;
         *     }
         *
         *
         *
         * 他的put也是调用的offer 非阻塞
         * take 是阻塞的
         */

        /**
         * poll部分的核心代码
         *  private E dequeue() {
         *         int n = size - 1;
         *         if (n < 0)
         *             return null;
         *         else {
         *         // （1）获取队列头元素
         *             Object[] array = queue;
         *             E result = (E) array[0];
         *             //（2）获取队尾元素 并且复制为null
         *             E x = (E) array[n];
         *             array[n] = null;
         *             Comparator<? super E> cmp = comparator;
         *             //(3)
         *             if (cmp == null)
         *                 siftDownComparable(0, x, array, n);
         *             else
         *                 siftDownUsingComparator(0, x, array, n, cmp);
         *             //(4)
         *             size = n;
         *             return result;
         *         }
         *     }
         *
         *
         *     private static <T> void siftDownComparable(int k, T x, Object[] array,
         *                                                int n) {
         *         if (n > 0) {
         *             Comparable<? super T> key = (Comparable<? super T>)x;
         *             int half = n >>> 1;           // loop while a non-leaf
         *             while (k < half) {
         *                 int child = (k << 1) + 1; // assume left child is least
         *                 Object c = array[child];
         *                 int right = child + 1;
         *                 if (right < n &&
         *                     ((Comparable<? super T>) c).compareTo((T) array[right]) > 0)
         *                     c = array[child = right];
         *                 if (key.compareTo((T) c) <= 0)
         *                     break;
         *                 array[k] = c;
         *                 k = child;
         *             }
         *             array[k] = key;
         *         }
         *     }
         */
    }
}
