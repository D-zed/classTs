package hanshunping.netty.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用nio进行文件的拷贝
 * @author dengzidi
 */
public class NioFileCopyDemo {

    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        File file = new File("F:\\HHHHHHH.txt");
        File fileTarget = new File("F:\\HHHHHHH1.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannelIn = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream(fileTarget);
        FileChannel fileChannelOut = fileOutputStream.getChannel();

        while (true){
            int read = fileChannelIn.read(byteBuffer);
            if (read==-1){
                break;
            }
            byteBuffer.flip();
            fileChannelOut.write(byteBuffer);
            byteBuffer.clear();
        }
        fileInputStream.close();
        fileOutputStream.close();

    }
}
