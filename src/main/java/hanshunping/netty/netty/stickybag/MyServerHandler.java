package hanshunping.netty.netty.stickybag;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<String> {


    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        //客户端发送的是十次但是 服务端接收次数并不固定 也即是粘包了
        System.out.println("服务器端接收到的数据 ："+msg);
        System.out.println("服务器接收消息数量 ="+(++this.count));
        ByteBuf byteBuf = Unpooled.copiedBuffer(UUID.randomUUID().toString(), CharsetUtil.UTF_8);
        ctx.channel().writeAndFlush(byteBuf);
    }

   /* @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
       byte [] buffer=new byte[msg.readableBytes()];
       msg.readBytes(buffer);
       //客户端发送的是十次但是 服务端接收次数并不固定 也即是粘包了
        System.out.println("服务器端接收到的数据 ："+new String(buffer, CharsetUtil.UTF_8));
        System.out.println("服务器接收消息数量 ="+(++this.count));
        ByteBuf byteBuf = Unpooled.copiedBuffer(UUID.randomUUID().toString(), CharsetUtil.UTF_8);
        ctx.channel().writeAndFlush(byteBuf);
    }*/

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
