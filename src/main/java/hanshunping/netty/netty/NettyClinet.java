package hanshunping.netty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClinet {
    public static void main(String[] args) {
        //需要一个事件循环组
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        //创建客户端的启动对象
        Bootstrap bootstrap=new Bootstrap();
        bootstrap.group(eventExecutors) //设置线程组
                .channel(NioSocketChannel.class) //设置客户端通道的实现类
                .
    }
}
