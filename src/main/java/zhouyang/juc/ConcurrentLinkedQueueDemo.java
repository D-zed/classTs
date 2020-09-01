package zhouyang.juc;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 并发包下的由单向链表构成的队列
 * 非阻塞队列
 * @author dzd
 */
public class ConcurrentLinkedQueueDemo {

    public static void main(String[] args) {
        ConcurrentLinkedQueue concurrentLinkedQueue=new ConcurrentLinkedQueue();
        concurrentLinkedQueue.offer("dd");
        //只获取不移除
        concurrentLinkedQueue.peek();
        //移除并返回
        concurrentLinkedQueue.poll();

        //这个方法在并发环境下不能满足安全因为cas没有加锁 在调用期间可能队列有增减
        concurrentLinkedQueue.size();

        //删除队列的第一个 dd元素 如果多个也只删除一个
        concurrentLinkedQueue.remove("dd");

        //线程不安全 由于遍历整个队列
        concurrentLinkedQueue.contains("dd");
        int t=5;

        if (t!=(t=8)){
            System.out.println("ddd");
        }

        restartFromHead:
        for (;;) {
            System.out.println("dddd");
            for (int i = 0; i < 10; i++) {
                if(i%2==0){
                    //goto restartFromHead 的位置
                    continue restartFromHead;
                }
            }
        }

    }




    //q永远代表尾节点的下一个节点
    //p t 指向尾节点
   /* public boolean offer(E e) {
   // todo 1校验空指针
        checkNotNull(e);
        // 2创建节点
        final ConcurrentLinkedQueue.Node<E> newNode = new ConcurrentLinkedQueue.Node<E>(e);

         todo 3从尾节点插入
        for (ConcurrentLinkedQueue.Node<E> t = tail, p = t;;) {
        //todo 4.注意每次循环的时候都会重新使q 指向 尾节点的下一个节点，用它的是否为空来做第一步是否可以抢占资源，
            ConcurrentLinkedQueue.Node<E> q = p.next;
            if (q == null) {
                // 5. cas 设置尾节点
                if (p.casNext(null, newNode)) {

                    6.设置成功了判断 p 和 t的关系此时pt相等 成功了 正常来讲是不相等的 如果 恰好有另一个也到了这一步那么则相等了 这步也是控制多线程的情况下 尾节点的设置问题
                    if (p != t) // hop two nodes at a time
                        casTail(t, newNode);
                    return true;
                }

            }
            // 7 这种情况是代表 头尾自引用了， 当poll移除元素的时候可能出现这种操作
            else if (p == q)

                p = (t != (t = tail)) ? t : head;
            else
                //8 这种情况是 在判断期间出现了 其他节点首先抢占 使得瞬间 q被赋值 这时候 p就会重新指向尾节点 q
                //这个寻找尾节点的
                p = (p != t && t != (t = tail)) ? t : q;
        }
    }*/




    /*
    public E poll() {
        // (1) goto
        restartFromHead:
        // (2) 无限循环
        for (;;) {
            for (Node<E> h = head, p = h, q;;) {
            //(3) 保存当前节点
                E item = p.item;

                //(4) 当前节点有值则cas变为null
                if (item != null && p.casItem(item, null)) {
                    // (5) CAS成功则标记当前节点并从链表中移除
                    if (p != h)
                        updateHead(h, ((q = p.next) != null) ? q : p);
                    return item;
                }
                //(6) 当前队列为空则返回null
                else if ((q = p.next) == null) {
                    updateHead(h, p);
                    return null;
                }
                //(7) 如果当前节点被自引用了，则重新寻找新的队列头节点
                else if (p == q)
                    continue restartFromHead;
                else //(8)
                    p = q;
            }
        }
    }

    final void updateHead(ConcurrentLinkedQueue.Node<E> h, ConcurrentLinkedQueue.Node<E> p) {
        if (h != p && casHead(h, p))
            h.lazySetNext(h);
    }
     */
}
