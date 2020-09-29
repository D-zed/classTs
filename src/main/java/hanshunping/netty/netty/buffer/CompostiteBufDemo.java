package hanshunping.netty.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

/**
 * 对外内存虽然表面上处于对外 实则java在分配堆外内存的时候依然收到jvm的约束 java程序可以操作的直接内存大小也是受制于jvm
 * 这个在jvm参数配置中可以配置，如果直接内存的大小超出了预期也会出现oom
 * @author dzd
 */
public class CompostiteBufDemo {

    public static void main(String[] args) {
        //复合缓冲区
        CompositeByteBuf byteBufs = Unpooled.compositeBuffer();
        //使用堆内存
        ByteBuf heapBuf=Unpooled.buffer(10);
        //使用直接内存
        ByteBuf directBuf=Unpooled.directBuffer(8);



    }
}
