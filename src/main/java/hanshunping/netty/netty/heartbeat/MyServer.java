package hanshunping.netty.netty.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class MyServer {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //加入一个空闲状态handler idlestate
                            //netty提供的处理空闲状态的一个处理器
                            //readerIdleTime 表示多长时间没有读，就会发送心跳检测客户端是否活着
                            //writerIdleTime 表示多长时间没有写，就会发送一个心跳检测包给客户端
                            //allIdle 表示多长时间没有读写 就会发送一个心跳检测包
                            //当{@link Channel}一段时间未执行*读，写或两者操作时，触发{@link IdleStateEvent}。
                            //使用心跳可以准实时的检测到客户端的连接状态
                            //当 idleStateEvent事件触发了就会调用下一个handler的userEventTrigger，在该方法中进行处理
                            //且该事件可能是读空闲写空闲 或者读写空闲
                            pipeline.addLast(new IdleStateHandler(3,5,7, TimeUnit.SECONDS));
                            //加入一个对空闲检测进一步处理的handler
                            pipeline.addLast(new MyServerHandler());

                            //
                            pipeline.addLast(new ChunkedWriteHandler());
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(8089).sync();
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()){
                        System.out.println("myServer启动成功");
                    }
                }
            });
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
