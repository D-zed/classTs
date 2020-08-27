package hanshunping.netty.netty.stickybag;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyMsgToByHandler extends MessageToByteEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        System.out.println("MyMsgToByHandler encode"+msg.array());
        out.writeInt(msg.array().length);
        out.writeBytes(msg.array());
    }
}
