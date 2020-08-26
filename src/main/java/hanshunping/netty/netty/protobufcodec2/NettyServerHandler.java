package hanshunping.netty.netty.protobufcodec2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * 我们自定义一个handler 需要继承netty规定好的某个handler适配器
 * 也就是遵循这个适配器规范的handler才称之为hanlder
 *
 * @author dengzidi
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {


    /**
     * 数据读取完毕
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //写到缓冲区并且刷新 写到管道
        //一般讲 我们对发送的数据进行编码
        System.out.println("channelReadComplete");
        int i = new Random().nextInt(3);
        MyDataInfo.MyMessage myMessage = null;
        if (0 == i) {
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDateType(MyDataInfo.MyMessage.DateTypep.StudentType)
                    .setStudent(MyDataInfo.Student.newBuilder().setId(5).setName("小煞笔").build()).build();
        } else {
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDateType(MyDataInfo.MyMessage.DateTypep.WorkerType)
                    .setWorker(MyDataInfo.Worker.newBuilder().setAge(20).setName("小煞笔").build()).build();
        }

        ctx.writeAndFlush(myMessage);
    }

    /**
     * 处理异常的方法 ,出现异常则关闭 通道
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {
        MyDataInfo.MyMessage.DateTypep dateType = msg.getDateType();
        if (dateType == MyDataInfo.MyMessage.DateTypep.StudentType) {
            MyDataInfo.Student student = msg.getStudent();
            System.out.println("服务端读取消息  学生的id" + student.getId() + "学生姓名" + student.getName());
        } else if (dateType == MyDataInfo.MyMessage.DateTypep.WorkerType) {
            MyDataInfo.Worker worker = msg.getWorker();
            System.out.println("服务端读取消息  工人的年龄" + worker.getAge() + "工人的姓名" + worker.getName());
        } else {
            System.out.println("服务端读取消息  类型不对");
        }

    }
}
