package hanshunping.netty.nio;

import java.nio.channels.SocketChannel;

/**
 * 当客户端链接 时通过 ServerSocketChannel 获取 SocketChannel
 * 将SocketChannel注册到 Selector
 *  register方法注册
 *  注册之后会返回一个selectorkey 与selector关联起来
 *  selector使用select方法进行监听 有事件发生的通道的个数 默认是阻塞的
 *  且这个事件已经定义在 SelectionKey中了 OP_ACCEPT OP_CONNECT OP_WRITE OP_READ
 *  并且监听到了可以得到对应的selectKey 并且通过selectKey反方向获取  注册的channel
 *  并且最后可以通过channel完成业务处理
 * @author dzd
 */
public class NioSelectorDemo {
    public static void main(String[] args) {
        //SocketChannel
    }
}
