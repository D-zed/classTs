package hanshunping.netty.nio;

import java.nio.Buffer;
import java.nio.IntBuffer;

/**
 * buffer
 * @author dengzidi
 * nio 是非阻塞的  bio是阻塞的
 * bio基于流的 nio是基于 channel buffer总是将数据从通道和缓冲区中进行
 * 传输
 *
 * selector 可以对多个通道事件进行监听从而达到单个线程监听多个客户端通道的目的
 *  channel就代表一个socket链接
     *           thread
     *             |
 *             selector
     *  |         |             |
 *    channel   channel    channel
 *    |         |             |
     buffer   buffer       buffer
    三个channel注册到该selector
    程序切换到哪个channel是由时间决定的 event 就是一个重要的概念
  selector会根据不同的时间在各个通道上切换
   buffer的理解  buffer我们可以理解为一个内存块底层是一个数组

 缓冲区是双向的可以读也可以写 双向的 不像bio中的输入输出流
 channel是双向的 可以返回底层操作系统的情况 比如linux 底层的操作系统就是双向的
 nio是事件驱动的

 buffer
 缓冲区本质上是一个可以读写数据的内存块 可以理解成是一个容器对象，其中含有数组
 且缓冲区对象内置的机制能够跟踪和记录缓冲区的状态比那花channel提供通道，传输数据
 都是从缓冲区
 nio程序 <---data-->  缓冲区 <--channel---> 文件


 buffer 中的属性
 position 位置
 hb 真实的数据
 mark 标记
 capacity 容量
 limit 缓冲区的读取终点
 flip的时候limit=position  position=0

 除了boolean类型的 其他基本类型都有其对应的buffer

 事实上最常用的是 ByteBuffer 因为字节是无敌的
 */
public class BasicBuffer {

    public static void main(String[] args) {
        //nio的缓冲区概念
        //创建一个intbuffer可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            //将数据放入缓冲区
            intBuffer.put(i*2);
        }
        //指定缓冲区中的位置添加
        intBuffer.put(0,1000);
        //buffer读取数据  模式切换 切换成读模式
        intBuffer.flip();
        //手动指定position 使得前两位不读取
        intBuffer.position(1);
        intBuffer.limit(4);
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }

        //初始化各个标识属性重置 但是数据并不真实的清空
        intBuffer.clear();
        System.out.println("clear ----------"+intBuffer.position()+"----"+intBuffer.limit()+"----"+intBuffer.capacity()+"----"+intBuffer.mark());
        //获取缓冲过区对应位置
        int i = intBuffer.get(2);
        System.out.println(i);

        System.out.println("可以再次写");
        for (int ii = 0; ii < intBuffer.capacity(); ii++) {
            //将数据放入缓冲区
            intBuffer.put(ii*3);
        }
        intBuffer.flip();

        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
