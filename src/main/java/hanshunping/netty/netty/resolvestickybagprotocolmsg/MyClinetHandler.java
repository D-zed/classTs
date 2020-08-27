package hanshunping.netty.netty.resolvestickybagprotocolmsg;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class MyClinetHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {


    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //使用客户端发送十条数据
        for (int i = 0; i < 5; i++) {
            String msg="今天天气冷，吃火锅";
            byte[] bytes = msg.getBytes(CharsetUtil.UTF_8);
            MessageProtocol messageProtocol = new MessageProtocol();
            messageProtocol.setContent(bytes);
            messageProtocol.setLen(bytes.length);
            ctx.writeAndFlush(messageProtocol);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
    }
}
