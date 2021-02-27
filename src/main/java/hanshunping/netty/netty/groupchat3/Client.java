package hanshunping.netty.netty.groupchat3;

import io.netty.bootstrap.Bootstrap;
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

public class Client {
    int port;
    String host;

    public Client(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void run(){
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("encoder",new StringEncoder());
                            pipeline.addLast("decoder",new StringDecoder());
                            pipeline.addLast(new ClientHand());
                        }
                    });
            //连接
            ChannelFuture sync = bootstrap.connect(host, port).sync();
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
               sync.channel().writeAndFlush(scanner.nextLine());
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }

    }
    public static void main(String[] args) {
        new Client(3636,"127.0.0.1").run();
    }

}