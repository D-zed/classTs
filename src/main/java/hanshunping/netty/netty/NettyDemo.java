package hanshunping.netty.netty;

/**
 * netty模型  主要依从 主从reactor多线程模型 mainreactor  subreactor
 * BossGroup线程部分
 *    维护selector 著关注Accept
 *    当接受到Accept事件获取到对应的SocketChannel 封装成NioSocketChannel并注册到worker线程的selector并进行维护，
 *    当worker线程监听到selector中通道发生自己感兴趣的事件进行处理
 *
 * netty
 *  抽象出来两组线程池
 *    bossGroup专门负责客户端的连接
 *    workerGroup 专门负责网络的读写
 *    bossGroup和WorkerGroup都抽象成了 NioEventLoopGroup  nio事件循环组，每个组中包含多个事件循环，每个事件循环是NioEventLoop
 *    NioEventLoop都有一个selector 表示不断循环的执行处理任务的线程。用于监听绑定在其上的socket的网络通讯
 *  NioEventLoopGroup可以有多个线程
 * 每个bossGroup的执行步骤：
 *   轮询Accept事件
 *   处理accept事件，与client建立连接，生成NioSocketChannel，并将注册到某个Worker 的NioEvent
 * WorkerGroup
 * @author dzd
 */
public class NettyDemo {
}
