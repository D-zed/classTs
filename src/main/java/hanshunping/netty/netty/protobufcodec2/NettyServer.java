package hanshunping.netty.netty.protobufcodec2;


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
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.util.NettyRuntime;

/**
 *  http +json一般用在 客户端和浏览器  protobuf一般用在服务与服务之间
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
                    .channel(NioServerSocketChannel.class) //使用NioServerChannel作为服务器通道的实现 (给bossgroup)
                    .option(ChannelOption.SO_BACKLOG, 128)//设置线程队列的连接个数  这个是设置bossGroup的队列，因为处理连接请求时串行处理的
                    .childOption(ChannelOption.SO_KEEPALIVE, true) //设置保持活动连接状态  一直保持连接的活动状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {    //给workergroup用的是 socketchannel
                        //向pipeline 设置处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("客户注册的时候的 channel--" + ch.hashCode());
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("encoder",new ProtobufEncoder());
                            pipeline.addLast("decoder",new ProtobufDecoder(MyDataInfo.MyMessage.getDefaultInstance()));
                            pipeline.addLast(new NettyServerHandler());

                        }
                    }); //给workergroup 的eventloop 对应的管道设置处理器
            System.out.println("。。。。 服务器 is ready 。。。。");

            //服务器启动，并且同步绑定端口，
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
            sync1.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }
}
