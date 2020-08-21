package hanshunping.netty.netty.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

public class GroupChatClient {
    private int port;
    private String host;

    public GroupChatClient( String host,int port) {
        this.port = port;
        this.host = host;
    }

    public void run() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
       try {
           Bootstrap bootstrap = new Bootstrap();
           bootstrap.group(group)
                   .channel(NioSocketChannel.class)
                   .option(ChannelOption.SO_KEEPALIVE,true)
                   .handler(new ChannelInitializer<SocketChannel>() {
                       @Override
                       protected void initChannel(SocketChannel ch) throws Exception {
                           ChannelPipeline pipeline = ch.pipeline();
                           pipeline.addLast("encoder",new StringEncoder()).addLast("decoder",new StringDecoder())
                                   .addLast(new GroupChatClientHandler());
                       }
                   });
           ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
           Channel channel = channelFuture.channel();
           Scanner scanner = new Scanner(System.in);
           while (scanner.hasNextLine()){
               String s = scanner.nextLine();
               channel.writeAndFlush(s+"\r\n");
           }
           channelFuture.channel().closeFuture().sync();
       }finally {
           group.shutdownGracefully();
       }
    }

    public static void main(String[] args) throws Exception {
        new GroupChatClient("127.0.0.1",8089).run();
    }
}
