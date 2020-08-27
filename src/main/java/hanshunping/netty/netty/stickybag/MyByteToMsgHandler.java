package hanshunping.netty.netty.stickybag;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

import java.util.List;

public class MyByteToMsgHandler extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int i = in.readInt();
        System.out.println("MyByteToMsgHandler decode  " +i);

        byte [] aa=new byte[i];
        in.readBytes(aa);
        out.add(new String(aa, CharsetUtil.UTF_8));
    }
}
