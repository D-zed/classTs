package hanshunping.netty.netty.protobuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * 我们自定义一个handler 需要继承netty规定好的某个handler适配器
 * 也就是遵循这个适配器规范的handler才称之为hanlder
 * @author dengzidi
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<StudentPojo.Student> {



    /**
     * 数据读取完毕
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //写到缓冲区并且刷新 写到管道
        //一般讲 我们对发送的数据进行编码
        StudentPojo.Student giao = StudentPojo.Student.newBuilder().setName("giao").setId(666).build();
        ctx.writeAndFlush(giao);
    }

    /**
     * 处理异常的方法 ,出现异常则关闭 通道
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, StudentPojo.Student msg) throws Exception {
        System.out.println("客户端发送的数据 id ="+msg.getId()+" name="+msg.getName());
    }
}
