package hanshunping.netty.nio;

import io.netty.util.CharsetUtil;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ByteBuffer<--- MappedByteBuffer<--- DirectByteBuffer
 * MappedByteBuffer 的能力 可以使文件直接在内存中（堆外内存）修改，
 * 不必加载到jvm中操作
 * @author dengzidi
 */
public class FileReadWriteBufferDemo {

    public static void main(String[] args) throws Exception {


        RandomAccessFile randomAccessFile = new RandomAccessFile("F:\\HHHHHHH.txt","rw");
        //获取对应的文件通道

        ByteBuffer buffer = ByteBuffer.allocate((int) randomAccessFile.length());
        randomAccessFile.getChannel().read(buffer);


        System.out.println(new String(buffer.array()));
        randomAccessFile.close();
    }
}
