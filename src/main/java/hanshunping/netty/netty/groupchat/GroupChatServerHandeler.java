package hanshunping.netty.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;


public class GroupChatServerHandeler extends SimpleChannelInboundHandler<String> {

    //定义一个channel组 管理所有的channel
    //全局事件执行器是一个单例
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        System.out.println("channelRead0");
        Channel channel = ctx.channel();
        //我们要排除自己
        for (Channel ch : channelGroup) {
            if (ch != channel) {
                System.out.println("我自己");
                ch.writeAndFlush("[客户]" + channel.remoteAddress() + "发送了消息 msg ：" + msg + "\n");
            } else {
                System.out.println("不是我自己");
                ch.writeAndFlush("[自己发送]" + msg + "\n");
            }
        }
    }

    //表示连接建立 第一个被执行的
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //将当前的channel 加入到 channelGroup
        Channel channel = ctx.channel();
        System.out.println("handlerAdded ---"+channel);
        //因为是群聊，该方法会将 channelGroup中的所有的channel遍历
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "加入聊天" + LocalDateTime.now() + "\n");
        channelGroup.add(channel);
        System.out.println("handlerAdded channel group"+channelGroup+"--");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //表示channel处于活动的状态 ，提示 xx上线
        System.out.println(ctx.channel().remoteAddress() + "上线了\n");

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //表示 channel不活跃 xx下线了
        System.out.println(ctx.channel().remoteAddress() + "下线了\n");
    }

    //断开连接，将xx客户离开信息推送给当前在线的客户
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "离开了\n");
        System.out.println("当前channelGroup size --> " + channelGroup.size());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("错误了关闭通道");
        ctx.close();
    }
}
