package hanshunping.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * 我们自定义一个handler 需要继承netty规定好的某个handler适配器
 * 也就是遵循这个适配器规范的handler才称之为hanlder
 * @author dengzidi
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * @param ctx 1.ctx 上下文对象 含有 管道pipeline 通道
     * @param msg 客户端发送的数据默认Object
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //如果处理逻辑非常的费时间
        //由于这种方式遇到处理逻辑复杂的方法则会倒是阻塞于此
        //找到当前channel绑定的 事件循环线程
        ctx.channel().eventLoop().execute(()->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("服务器读取线程 -" + Thread.currentThread().getName());
            System.out.println("server ctx =" + ctx);
            Channel channel = ctx.channel();
            ChannelPipeline pipeline = ctx.pipeline();
            //将msg转成 一个bytebuffer
            //这个byteBuf是netty提供的 并且性能更好
            ByteBuf buf = (ByteBuf) msg;
            System.out.println("客户端发送的消息是" + buf.toString(CharsetUtil.UTF_8));

            System.out.println("客户端地址" + ctx.channel().remoteAddress());

        });

        //这个例子主要是为了说明 同一个channel的eventloop对应的是一个线程池 虽然他的taskQueue是多个
        //但是他们是多个任务相当于一个同步队列
        ctx.channel().eventLoop().execute(()->{
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("服务器读取线程 -" + Thread.currentThread().getName());
            System.out.println("server ctx =" + ctx);

        });

        ctx.channel().eventLoop().schedule(()->{
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("服务器读取线程 -" + Thread.currentThread().getName());
            System.out.println("server ctx =" + ctx);
        },5,TimeUnit.SECONDS);


        System.out.println("go on ...");
    }

    /**
     * 数据读取完毕
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //写到缓冲区并且刷新 写到管道
        //一般讲 我们对发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端",CharsetUtil.UTF_8));
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
}
