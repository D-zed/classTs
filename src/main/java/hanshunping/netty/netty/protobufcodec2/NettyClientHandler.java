package hanshunping.netty.netty.protobufcodec2;

import hanshunping.netty.netty.protobuf.StudentPojo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class NettyClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
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
        //改造为随机发送student 或者worker
        int i = new Random().nextInt(3);
        MyDataInfo.MyMessage myMessage=null;
        if (0==i){
             myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDateType(MyDataInfo.MyMessage.DateTypep.StudentType)
                    .setStudent(MyDataInfo.Student.newBuilder().setId(5).setName("小煞笔").build()).build();
        }else {
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDateType(MyDataInfo.MyMessage.DateTypep.WorkerType)
                    .setWorker(MyDataInfo.Worker.newBuilder().setAge(20).setName("小煞笔").build()).build();
        }

        ctx.writeAndFlush(myMessage);

    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {
        MyDataInfo.MyMessage.DateTypep dateType = msg.getDateType();
        if (dateType==MyDataInfo.MyMessage.DateTypep.StudentType){
            MyDataInfo.Student student = msg.getStudent();
            System.out.println("客户端读取消息  学生的id"+student.getId()+"学生姓名"+student.getName());
        }else if (dateType==MyDataInfo.MyMessage.DateTypep.WorkerType){
            MyDataInfo.Worker worker = msg.getWorker();
            System.out.println("客户端读取消息  工人的年龄"+worker.getAge()+"工人的姓名"+worker.getName());
        }else {
            System.out.println("客户端读取消息  类型不对");
        }

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
