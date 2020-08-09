package hanshunping.netty.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * nio是一个同步非阻塞模型
 * java 1.4之后出现的
 * 是一个基于 通道 和 缓冲区 还有selector（选择器）
 * @author dengzidi
 */
public class NioFileChannelWriteDemo {

    private static ByteBuffer byteBuffer;

    /**
     * 使用nio将数据写入到文件中
     *  java输出流 包裹了 NioFileChannel
     */
    public static void main(String[] args) throws IOException {

        String str="hello 小伙子";
        //因为nio也是基于java的io的所以也要使用到输出流
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\HHHHHHH");
        FileChannel channel = fileOutputStream.getChannel();
        byteBuffer = ByteBuffer.allocate(1024);
        //将字节写入到字节 缓冲区
        byteBuffer.put(str.getBytes());
        //把bytebuffer读
        byteBuffer.flip();
        //将缓冲区的数据写入到channel中
        channel.write(byteBuffer);
        fileOutputStream.close();
    }
}
