package hanshunping.netty.nio.groupchat;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 群聊的server
 * 此服务只起到了一个中间转发的功能 ，其他的客户端之间互相通信
 *
 * @author dzd
 */
public class GroupChatServer {

    private Selector selector;

    private ServerSocketChannel listenChannel;

    private static final int PORT = 3333;

    /**
     * 初始化工作
     */
    public GroupChatServer() {
        try {
            //得到选择器
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            //设置非阻塞模式  selector上注册必须是非阻塞模式的通道
            listenChannel.configureBlocking(Boolean.FALSE);
            //注册连接事件
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void listen() {
        try {
            while (true) {
                int count = selector.select(2000);
                if (count > 0) {
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        //如果key是监听事件
                        if (key.isAcceptable()) {
                            SocketChannel clientChanel = listenChannel.accept();
                            clientChanel.configureBlocking(Boolean.FALSE);
                            clientChanel.register(selector, SelectionKey.OP_READ);
                            System.out.println(clientChanel.getRemoteAddress() + "上线");
                        }
                        if (key.isReadable()) {
                            readData(key);
                        }
                        iterator.remove();
                    }

                } else {
                    // System.out.println("select目前没有事件发生");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readData(SelectionKey key) {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int count = channel.read(byteBuffer);
            if (count > 0) {
                String s = new String(byteBuffer.array());
                //读出来对应的信息
                System.out.println("from 客户端" + s);
                //向其他的客户端转发消息
                sendMsgToOtherClient(s, channel);
            }
        } catch (Exception e) {
            try {
                System.out.println(channel.getRemoteAddress() + "下线了");
                //将key从selector上解除注册
                key.cancel();
                //将通道关闭
                channel.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }


    /**
     * 转发消息给其他的客户端 也就是其他的通道
     *
     * @param self self
     * @param msg  msg
     */
    private void sendMsgToOtherClient(String msg, SocketChannel self) {

        try {
            System.out.println("消息转发中");
            Set<SelectionKey> keys = selector.keys();
            for (SelectionKey key : keys) {
                Channel targetChannel = key.channel();
                if (targetChannel instanceof SocketChannel && targetChannel != self) {
                    SocketChannel dest = (SocketChannel) targetChannel;
                    ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                    dest.write(buffer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
