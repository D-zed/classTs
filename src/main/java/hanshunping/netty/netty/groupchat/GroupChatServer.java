package hanshunping.netty.netty.groupchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 1一个EventLoopGroup 当中会包含一个或多个EventLoop
 * 2一个EventLoop在整个生命周期中智慧与唯一一个Thread绑定
 * 3所有由EventLoop所处理的各种io事件都将在它锁关联的哪个Thread上进行处理
 * 4一个channel在他的整个生命周期中只会注册在一个EventLoop上
 * 5一个EventLoop在运行过程当中，会被分配给一个或者多个channel
 *
 * jdk的future使用的get方法是会阻塞的使用的是 lockPark方法使用阻塞队列
 * 而netty则使用了 channelfuture方法 则使用了观察者模式 使用回调函数
 *
 * 千万不要在handler中做阻塞 因为一个eventLoop对应的是一个线程
 * 而这个eventLoop有对应着多个channel处理每个channel上的事件
 * 所以一旦在其中阻塞了则会造成大规模阻塞
 *
 * 回看71  看到了 75
 * @author dzd
 */
public class GroupChatServer {

    //监听端口
    private int port;

    public GroupChatServer(int port) {
        this.port = port;
    }

    public void run() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    //channelOption中主要维护的是 配置的数据目的是为了设置 channelConfig
                    //attributeKey attribute 中维护的是一个业务数据我们可以在想要使用的域中取出来
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("decoder",new StringDecoder());
                            pipeline.addLast("encoder",new StringEncoder());;
                            pipeline.addLast(new GroupChatServerHandeler());
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("8089启动成功");
                    }
                }
            });
            //sync是变异步为同步   阻塞住防止主线程over
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        /**
         * 复数的二进制是其反码加1
         * 6   110
         * 反码 001
         *     010
         *
         * 当时一个二的指数倍的数
         * 4   100
         * 反码 011
         * 加1  100
         * 所以以下表达式是为了判断当前值是不是2的幂
         */
        int val=6; //2的二进制是 10   -10
        int i = val & -val;
        System.out.println(i);
        //GroupChatServer groupChatServer = new GroupChatServer(8089);
        //groupChatServer.run();
    }
}
