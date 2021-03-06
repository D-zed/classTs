package hanshunping.netty.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;


/**
 * ctx 对handler进行了包装 并且 其可以方便 本handler与此context所在的pipeline中的handler进行交互
 * 调用fire。。的方法
 * 并且ctx可以作为引用保存，并在其他线程业务或者 其他线程中调用 都是可以的
 * 允许获取一个上下文对象，后续使用
 *
 * 这个具体实现 SimpleChannelInboundHandler 还是 ChannelInboundHandlerAdapter 由具体场景而异
 * SimpleChannelInboundHandler 其中也是对 ChannelInboundHandlerAdapter进行继承使用了模板模式
 * 其中我们的消息处理逻辑我们自己处理  但是他自带帮助gc 回收消息
 * 如果我们的msg处理之后还有别的用处那么就要考虑是否要用这个处理器了当然如果是发送一次无需存储的使用这个是最方便不过了
 * @author dzd
 */
public class GroupChatServerHandeler extends SimpleChannelInboundHandler<String> {

    //定义一个channel组 管理所有的channel
    //全局事件执行器是一个单例
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    //表示连接建立 第一个被执行的
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //将当前的channel 加入到 channelGroup
        //这个地方使用channel 可以保证 经历pipeline中的每一个handler
        //使用 ctx.write 则会漏掉一些 handler
        Channel channel = ctx.channel();
        System.out.println("handlerAdded ---"+channel);
        //因为是群聊，该方法会将 channelGroup中的所有的channel遍历
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "加入聊天" + LocalDateTime.now() + "\n");
        channelGroup.add(channel);
        System.out.println("handlerAdded channel group"+channelGroup+"--");
    }


    //断开连接，将xx客户离开信息推送给当前在线的客户
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "离开了\n");
        System.out.println("当前channelGroup size --> " + channelGroup.size());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //表示channel处于活动的状态 ，提示 xx上线
        System.out.println(ctx.channel().remoteAddress() + "上线了\n");

    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        System.out.println("channelRead0");
        Channel channel = ctx.channel();
        //我们要排除自己
        for (Channel ch : channelGroup) {
            if (ch != channel) {
                System.out.println("不是我自己");
                ch.writeAndFlush("[客户]" + channel.remoteAddress() + "发送了消息 msg ：" + msg + "\n");
            } else {
                System.out.println("我自己");
                ch.writeAndFlush("[自己发送]" + msg + "\n");
            }
        }
    }





    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //表示 channel不活跃 xx下线了
        System.out.println(ctx.channel().remoteAddress() + "下线了\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println(cause);
        System.out.println("错误了关闭通道");
        ctx.close();
    }
}
