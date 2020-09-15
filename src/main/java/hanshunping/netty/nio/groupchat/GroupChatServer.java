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
import java.util.concurrent.locks.LockSupport;

/**
 * 群聊的server
 * 此服务只起到了一个中间转发的功能 ，其他的客户端之间互相通信
 *  selectionKey 绑定了selector 和 channel的管理
 *
 *  1.这个 就是 单reactor 单线程的模型，因为只有一个 selector  一个线程处理所有的事件
 *  所以当更多的并发过来，如果你的handler处理的速度有一些很复杂，那么就不能使用这种
 *  因为所有的事件都排队在selector中等待处理，然后如果出现了一个卡顿就会使得整个
 *  handler处理阻塞状态，问题就很大，然后优点在于简单
 *
 *  2.下边介绍单reactor多线程handler
 *    1.reactor对象通过select监控客户端的请求事件收到事件后通过dispatch进行分发
 *    2。如果建立连接请求则由Acceptor通过accept处理连接请求，然后创建处理完成连接
 *    后的各种事件
 *    3.如果不是连接请求，则有reactor分发掉哟了那个连接对应的handler来处理
 *    4.handler只负责响应事件，不做具体的业务处理，通过read读取数据后会分发给
 *    后边的worker线程池的某个线程来处理业务
 *    5.worker线程处分配独立的线程完成真正的业务，并将结果返回给handler
 *    6.handler收到响应后通过send将结果返回给client （正是因为这个worker的异步使得
 *    性能大增，并且使得处理的不阻塞也就是handler的不阻塞）
 *    优点：可以充分的利用多核cpu的处理能力
 *    缺点：由于只有一个reactor监听连接所以容易出现高并发模式下的连接处理部分的瓶颈
 *         多线程数据共享和访问比较复杂
 *  3.下边介绍主从reactor多线程模式
 *    1.reactor主线程mainreacotr对象通过select监听连接事件 通过Acceptor处理连接事件
 *    2.当acceptor处理连接事件后，mainreactor将连接分配给subreactor
 *    3.subreactor将连接加入到连接队列进行监听，并创建handler进行各种事件处理
 *    4.当有新事件发生的时候，subreactor就会调用对应的handler处理
 *    5.handler通过read读取数据，分发给后边的worker线程处理
 *    6.worker线程池分配独立的worker线程，并返回结果
 *    7.handler收到响应结果后返回给client
 *    mainreactor可以关联多个subreactor
 *    mainreactor和 subreactor分开处理 可以理解为前台与服务员的关系，主线程和子线程的数据交互简单 主线程将新连接给子线程即可
 *    缺点编程复杂度高的很
 *    这种模型很多项目都在广泛的使用 nginx 主从reactor模型 netty主从多线程模型的支持
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
            //下面的open使用的就是这个selectcor提供者，
            // SelectorProvider.provider().openServerSocketChannel();
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
