package hanshunping.netty.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteByfDemo {
    public static void main(String[] args) {
        //低下包含了以后个byte数组
        //netty的buffer中不需要进行flip
        //底层维护了 readerIndex 和 writeIndex
        //capacity 容量

        //0--readIndex 以读
        //readIndex -- writeIndex 可读
        //writeIndex -capacity可写

        ByteBuf buffer = Unpooled.buffer(20);
        for (int i = 0; i < buffer.capacity(); i++) {
           buffer.writeByte(i);
        }



        for (int i = 0; i < buffer.capacity(); i++) {
            //此读取方法可以改变读取的位置

            System.out.println(buffer.readByte()+"-----");
        }

    }
}
