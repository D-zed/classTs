package zhouyang.juc;


import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockQueueDemo {

    //之所以要学习阻塞队列是因为后边需要更好的理解线程池，队列也是线程安全的类，而且是用了reentrantlock

    //测试add remove  报错一族
    @Test
    public void test1() {
        //queue是个接口也是list的同级别 Collection，既然是Collection那么我们就应该知道 他的实现基本上就是数组和链表
        //数组队列 数组就要设置长度了
        BlockingQueue<Integer> blockingQueue=new ArrayBlockingQueue<>(3);
        //普通队列
        LinkedBlockingQueue<Integer> blockingQueue1=new LinkedBlockingQueue<Integer>(3);
        //这个是双向队列
        BlockingQueue<Integer> blockingQueue2=new LinkedBlockingDeque<Integer>();

        blockingQueue1.add(2);
        blockingQueue1.add(3);
        blockingQueue1.add(4);
        Integer element = blockingQueue1.element();
        System.out.println(element);
        //如果队列是空的也会报错
        blockingQueue.remove();
        //如果用add那么这个地方会报错
        blockingQueue1.add(2);
    }

    //测试 offer 和poll 不报错一族  其实add里边调用的就是offer 只是当offerfalse的时候add抛出了异常
    @Test
    public void test11() {
        //queue是个接口也是list的同级别 Collection，既然是Collection那么我们就应该知道 他的实现基本上就是数组和链表
        //数组队列 数组就要设置长度了
        BlockingQueue<Integer> blockingQueue=new ArrayBlockingQueue<>(3);
        //普通队列
        BlockingQueue<Integer> blockingQueue1=new LinkedBlockingQueue<Integer>(3);
        //这个是双向队列
        BlockingQueue<Integer> blockingQueue2=new LinkedBlockingDeque<Integer>();

        blockingQueue1.offer(2);
        blockingQueue1.offer(3);
        blockingQueue1.offer(4);
        Integer peek = blockingQueue1.peek();
        System.out.println(peek);
        System.out.println(JSON.toJSONString(blockingQueue1));
        blockingQueue.poll();

        //然而offer不会报错会返回false
        boolean offer = blockingQueue1.offer(1);
        System.out.println(offer);

    }


    //测试 offer 和poll 的带参数重构方法 延迟添加
    @Test
    public void test111() throws InterruptedException {
        //queue是个接口也是list的同级别 Collection，既然是Collection那么我们就应该知道 他的实现基本上就是数组和链表
        //数组队列 数组就要设置长度了
        BlockingQueue<Integer> blockingQueue=new ArrayBlockingQueue<>(3);
        //普通队列
        BlockingQueue<Integer> blockingQueue1=new LinkedBlockingQueue<Integer>(3);
        //这个是双向队列
        BlockingQueue<Integer> blockingQueue2=new LinkedBlockingDeque<Integer>();

        blockingQueue1.offer(2);
        blockingQueue1.offer(3);
        blockingQueue1.offer(4);
        Integer poll = blockingQueue.poll(1, TimeUnit.SECONDS);
        System.out.println(poll);

        //然而offer不会报错会返回false
        boolean offer = blockingQueue1.offer(1,2, TimeUnit.SECONDS);
        System.out.println(offer);

    }


    //测试 put
    @Test
    public void test1111() throws InterruptedException {
        //queue是个接口也是list的同级别 Collection，既然是Collection那么我们就应该知道 他的实现基本上就是数组和链表
        //数组队列 数组就要设置长度了
        BlockingQueue<Integer> blockingQueue=new ArrayBlockingQueue<>(3);
        //普通队列
        LinkedBlockingQueue<Integer> blockingQueue1=new LinkedBlockingQueue<Integer>(3);
        //这个是双向队列
        BlockingQueue<Integer> blockingQueue2=new LinkedBlockingDeque<Integer>();

        blockingQueue1.put(2);
        blockingQueue1.put(3);
        blockingQueue1.put(4);
        Integer poll = blockingQueue.poll(1, TimeUnit.SECONDS);
        System.out.println(poll);

        //put就是如果队列满了则一直停在那里
        blockingQueue1.put(5);
        System.out.println(JSON.toJSONString(blockingQueue1));

    }
}
