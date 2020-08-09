package hanshunping.netty.nio;

import java.nio.ByteBuffer;

/**
 * @author dengzidi
 */
public class NioByteByteBufferPutGetDemo {

    public static void main(String[] args) {
        //这个put什么拿出来什么
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        byteBuffer.putInt(12);
        byteBuffer.putLong(22L);
        byteBuffer.putChar('山');

        byteBuffer.flip();
        //上下的类型如果不匹配则出现问题  BufferUnderflowException
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        //这个因为是按照类型的所占字节数取得一旦字节数多了就会溢出 或者出现数据错乱
        System.out.println(byteBuffer.getChar());


    }
}
