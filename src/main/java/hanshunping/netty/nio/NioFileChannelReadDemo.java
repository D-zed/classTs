package hanshunping.netty.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * nio是一个同步非阻塞模型
 * java 1.4之后出现的
 * 是一个基于 通道 和 缓冲区 还有selector（选择器）
 * nio读取文件
 * @author dengzidi
 */
public class NioFileChannelReadDemo {

    private static ByteBuffer byteBuffer;

    /**
     * 使用nio将数据写入到文件中
     *  java输出流 包裹了 NioFileChannel
     */
    public static void main(String[] args) throws IOException {


        File file = new File("F:\\HHHHHHH");
        //因为nio也是基于java的io的所以也要使用到输出流
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel   = fileInputStream.getChannel();
        byteBuffer = ByteBuffer.allocate((int)file.length());
        //将缓冲区的数据写入到channel中
        fileChannel.read(byteBuffer);
        byteBuffer.flip();
        byteBuffer.get();
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }
}
