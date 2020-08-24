package hanshunping.netty.netty.websocket;

import com.sun.corba.se.impl.interceptors.PICurrent;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketServer {
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler())
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //websocket是基于http协议之上的
                            pipeline.addLast(new HttpServerCodec());
                            //以块的方式写
                            pipeline.addLast(new ChunkedWriteHandler());
                            //当浏览器发送大量数据的时候会发出多次http请求，此处理器可以将大量数据聚合起来,此参数为聚合的最大值
                            pipeline.addLast(new HttpObjectAggregator(8092));
                            //websocket 是以帧 frame的方式传输的
                            //https://blog.csdn.net/u010487568/article/details/20569027
                            //可以看到 websocketframe 下面有六个子类
                            //浏览器请求时 ws://localhost:8099/xxx 表示请求uri
                            //此处理器将 http协议升级为 ws协议  保持长连接
                            //websocketServerProtocalHandler 升级http协议
                            //通过一个状态码 101
                            pipeline.addLast(new WebSocketServerProtocolHandler("/"));
                            //处理之后到达我们的websockethandler
                            //Status Code: 101 Switching Protocols
                            //升级成一个全双工的长连接性能非常的好
                            pipeline.addLast(new MyTextWebSocketFrameHandler());
                        }
                    });
            ChannelFuture sync = serverBootstrap.bind(7000).sync();
            sync.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()){
                        System.out.println("webscoket服务启动");
                    }
                }
            });
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
