package hanshunping.netty.netty.stickybag;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyClient {

    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //这个编码节码是为了能够在发送 的时候可以多发送一个int来指定本包的读取范围防止读取时候的粘包拆包问题
                            //编码作为一个出栈处理器 在handler之后执行对write方法进行编码 指定读取范围，至于可以封装一下 美其名曰 协议 其实最重要的还是这个出栈处理器
                            pipeline.addLast(new MyMsgToByHandler());
                            pipeline.addLast(new MyClinetHandler());
                        }
                    });
            ChannelFuture sync = bootstrap.connect("127.0.0.1", 7000).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
