package hanshunping.netty.nio;

import com.sun.org.apache.xerces.internal.dom.PSVIElementNSImpl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 *
 */
public class NioServerDemo {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel= ServerSocketChannel.open();
        Selector selector = Selector.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(2222));
        //服务端设置成非阻塞模式
        serverSocketChannel.configureBlocking(Boolean.FALSE);

        //把serverSocketChannel 注册到 selector 且关心的事件为 OP_ACCEPT
        //此通道为网络通道其关注的事件就是连接
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务器启动此时注册的通道数"+selector.keys().size());
        //循环等待客户端的链接
        while (true){
            //检查通道中是否有事件发生
            if (selector.select(1000)==0){
                //System.out.println("服务器等待1秒，无链接");
                continue;
            }

            //如果有事件发生则继续运行
            //获取所有的发生事件的通道
            //通过selectionkeys反向获取通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //遍历selectionKeys
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                //获取到selectionKey
                SelectionKey key = iterator.next();
                //判断对应的事件如果是accept 有新的客户端连接
                //事件驱动就在于此 所以accept得以不用像bio一样阻塞于此

                //显然这个key是 serverSocketChannel的key因为他关注的是accept事件
                if (key.isAcceptable()){
                    //此通道是客户端连接的通道需要再次注册
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(Boolean.FALSE);
                    System.out.println("新的客户端连接"+socketChannel.hashCode());
                    //注册的时候指定与之关联的缓冲区
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    //客户端连接后注册的
                    System.out.println("客户端连接后注册的selector "+selector.keys().size());
                }
                if (key.isReadable()){

                    //获取关联的通道
                    SocketChannel channel = (SocketChannel) key.channel();
                    //获取关联的共享数据
                    ByteBuffer buffer = (ByteBuffer) key.attachment();

                    //当然也可以获得与之关联的selector

                    //也可以改变其监听的事件
                    int i = key.interestOps();
                    System.out.println("监听事件是："+i);
                    channel.read(buffer);
                    buffer.clear();
                    System.out.println("from客户端"+new String(buffer.array()));
                }
                //事件处理完成将其移除
                iterator.remove();
            }

        }
    }
}
