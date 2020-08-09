package hanshunping.netty.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ByteBuffer<--- MappedByteBuffer<--- DirectByteBuffer
 * MappedByteBuffer 的能力 可以使文件直接在内存中（堆外内存）修改，
 * 不必加载到jvm中操作
 * @author dengzidi
 */
public class MappedByteBufferDemo {

    public static void main(String[] args) throws Exception {


        RandomAccessFile randomAccessFile = new RandomAccessFile("F:\\HHHHHHH.txt","rw");
        //获取对应的文件通道
        FileChannel channel = randomAccessFile.getChannel();
        //         标识对文件操作为读写模式
        //         起始位置
        //         映射到文件的大小（也就是可以将文件中的多少内容（字节）映射到内存中修改）
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0, (byte) 'h');
        mappedByteBuffer.put(3, (byte) 'h');
        randomAccessFile.close();
    }
}
