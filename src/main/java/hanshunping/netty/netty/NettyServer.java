package hanshunping.netty.netty;

import hanshunping.netty.netty.protobuf.StudentPojo;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.util.NettyRuntime;

/**
 * netty模型  主要依从 主从reactor多线程模型 mainreactor  subreactor
 * BossGroup线程部分
 * 维护selector 著关注Accept
 * 当接受到Accept事件获取到对应的SocketChannel 封装成NioSocketChannel并注册到worker线程的selector并进行维护，
 * 当worker线程监听到selector中通道发生自己感兴趣的事件进行处理
 * <p>
 * netty
 * 抽象出来两组线程池
 * bossGroup专门负责客户端的连接
 * workerGroup 专门负责网络的读写
 * bossGroup和WorkerGroup都抽象成了 NioEventLoopGroup  nio事件循环组，每个组中包含多个事件循环，每个事件循环是NioEventLoop
 * NioEventLoop都有一个selector 表示不断循环的执行处理任务的线程。用于监听绑定在其上的socket的网络通讯
 * NioEventLoopGroup可以有多个线程
 * 每个bossGroup的执行步骤：
 * 轮询Accept事件
 * 处理accept事件，与client建立连接，生成NioSocketChannel，并将注册到某个Worker 的NioEvent
 * <p>
 * <p>
 * 每个workerEventLoop执行任务的时候会使用 pipeline （其中包含了channel） 及通过管道获取对应的通道，管道中维护了许多处理器
 * <p>
 * NioEventLoop 代表一个不断循环的处理任务的线程 其中维护任务队列（taskQueue） 和线程
 * 包含一个selector，NioEventLoop的selector上可以注册多个NioChannel
 * 也就是说一个NioEventLoop对应多个NioChannel
 * 每个NioChannel都绑定有一个自己的ChannelPipeline 管道 （二者可以相互获取，channel包含了pipeline）
 * netty管道
 * WorkerGroup
 *
 * @author dzd
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        //创建两个事件循环线程组
        //二者分别处理连接请求和处理业务
        //二者都是无限循环
        //这个 线程数如果不设置的话 就是默认值  是 系统线程数*2 比如四核八线程 那就是 16
        //按理说boss线程组不需要太多线程数
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(8);

        System.out.println("线程组数 --" + NettyRuntime.availableProcessors());

        try {
            //创建服务器端的启动对象，配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup, workerGroup) //设置俩个线程组
                    .channel(NioServerSocketChannel.class) //使用NioServerChannel作为服务器通道的实现 (给bossgroup) 设置channelfactory
                    .option(ChannelOption.SO_BACKLOG, 128)//设置线程队列的连接个数  这个是设置bossGroup的队列，因为处理连接请求时串行处理的
                    .childOption(ChannelOption.SO_KEEPALIVE, true) //设置保持活动连接状态  一直保持连接的活动状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {    //给workergroup用的是 socketchannel
                        //向pipeline 设置处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("客户注册的时候的 channel--" + ch.hashCode());
                            ChannelPipeline pipeline = ch.pipeline();
                         //   pipeline.addLast("decoder",new ProtobufDecoder(StudentPojo.Student.getDefaultInstance()));
                            pipeline.addLast(new NettyServerHandler());

                        }
                    }); //给workergroup 的eventloop 对应的管道设置处理器
            System.out.println("。。。。 服务器 is ready 。。。。");

            //服务器启动，并且同步绑定端口，
            //bind方法的时候真正的触发 channelFactory的使用
            //张龙的 56节
            ChannelFuture sync = bootstrap.bind(6668).sync();

            //对关闭的通道进行监听
            ChannelFuture sync1 = sync.channel().closeFuture().sync();
            sync1.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("通道 " + future.channel() + "关闭");
                    }
                }
            });
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }
}
