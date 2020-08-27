package hanshunping.netty.netty.stickybag;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class MyClinetHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        int i = msg.readableBytes();
        byte [] buffer=new byte[i];
        msg.readBytes(buffer);
        System.out.println("客户端接收到的信息="+new String(buffer,CharsetUtil.UTF_8));
        System.out.println("客户端接收到的信息数量="+(++this.count));

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //使用客户端发送十条数据
        for (int i = 0; i < 5; i++) {
            ctx.channel().writeAndFlush(Unpooled.copiedBuffer("hello server"+i, CharsetUtil.UTF_8));
        }
    }
}
