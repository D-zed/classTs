package hanshunping.netty.netty.inboundandoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyByteToLongDecoder extends ByteToMessageDecoder {
    /**
     * decode方法会根据接收到的数据 多次调用 直到确定没有新的元素被添加到List
     * 或者bytebuf没有更多的可读字节
     * @param ctx  上下文对象
     * @param in  入站的bytebuf
     * @param out 处理之后的内容可以传递到下一个入站处理器处理
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
           if (in.readableBytes()>=8){
               long l = in.readLong();
               System.out.println("服务端处理 --"+l);
               out.add(l);
           }
    }
}
