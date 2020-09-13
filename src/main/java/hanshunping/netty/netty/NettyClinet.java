package hanshunping.netty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

public class NettyClinet {
    public static void main(String[] args) throws InterruptedException {
        /**
         * netty中默认的线程数NettyRuntime avaliableProcessors 是对下面的封装，
         * 并且获得的数是超线程之后的值
         */
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i*2);
        //需要一个事件循环组
        /**
         * Nio EventLoopGroup 实现了  MultithreadEventLoopGroup（基于nio的selector的实现）
         */
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        //创建客户端的启动对象
        Bootstrap bootstrap=new Bootstrap();
        try{
            bootstrap.group(eventExecutors) //设置线程组
                    .channel(NioSocketChannel.class) //设置客户端通道的实现类
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                          //  pipeline.addLast("encoder",new ProtobufEncoder());
                            //加入自己的处理器
                            pipeline.addLast(new NettyClientHandler());

                        }
                    });
            System.out.println("客户端准备好了");
            //启动客户端连接服务器端
            //netty的异步模型，后边讲
            ChannelFuture cf = bootstrap.connect("127.0.0.1", 6668);
            //给cf 注册监听器
            cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (cf.isSuccess()){
                        System.out.println("监听端口6668成功");
                    }else {
                        System.out.println("监听端口6668失败");
                    }
                }
            });


            //监听关闭的通道
            cf.channel().closeFuture().sync();
        }finally {
            eventExecutors.shutdownGracefully();
        }

    }
}
