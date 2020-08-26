package hanshunping.netty.netty.inboundandoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

//             粘包 拆包                                       void 代表用户状态的管理
//                                      void代表不需要用户状态管理

/**
 * @author ASUS
 * 这个类型的局限性  并且性能稍弱
 */
public class MyByteToLongDecoder2 extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        System.out.println("MyByteToLongDecoder2 解码器");
       //再replaying内部不需要判断数据是否到达读取的长度
        out.add(in.readLong());

    }
}
