package hanshunping.netty.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;

public class ByteByfDemo2 {
    public static void main(String[] args) {
        //低下包含了以后个byte数组
        //netty的buffer中不需要进行flip
        //底层维护了 readerIndex 和 writeIndex
        //capacity 容量

        //0--readIndex 以读
        //readIndex -- writeIndex 可读
        //writeIndex -capacity可写

        ByteBuf buffer = Unpooled.copiedBuffer("吃了么", CharsetUtil.UTF_8);

        if (buffer.hasArray()){
            byte[] array = buffer.array();
            for (int i = 0; i < array.length; i++) {
                System.out.println(i);
            }
            String s = new String(array, StandardCharsets.UTF_8);
            System.out.println(s);
            System.out.println(buffer.arrayOffset());
            System.out.println(buffer.readerIndex());
            System.out.println(buffer.writerIndex());
            System.out.println("buffer.readableBytes() before--"+buffer.readableBytes());
            System.out.println(buffer.readByte());
            System.out.println(buffer.getCharSequence(0,4,StandardCharsets.UTF_8));
            System.out.println("buffer.readableBytes() after--"+buffer.readableBytes());
            //容量会大于内容
            System.out.println(buffer.capacity());
        }
    }
}
