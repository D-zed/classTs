package hanshunping.netty.netty.protobuf;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyClientHandler extends SimpleChannelInboundHandler<StudentPojo.Student> {
    /**
     * 通道就绪的时候就会触发该消息
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("clinet "+ctx);
        //ctx.writeAndFlush(Unpooled.copiedBuffer("hello buffer  喵", CharsetUtil.UTF_8));
        //----改造为protobuff
        StudentPojo.Student student = StudentPojo.Student.newBuilder().setId(4).setName("林冲").build();
        ctx.writeAndFlush(student);

    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, StudentPojo.Student msg) throws Exception {

        System.out.println("客户端接收消息："+msg.getId()+"--"+msg.getName());
    }

    /**
     * 处理异常
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
