package hanshunping.netty.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 另一种文件copy方式
 * @author dengzidi
 */
public class FileChannelCopyDemo2 {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("F:\\HHHHHHH.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\HHHHHHH2.txt");

        FileChannel source = fileInputStream.getChannel();
        FileChannel target = fileOutputStream.getChannel();

        //将source的copy到target
        target.transferFrom(source,0,source.size());

        //关闭相关的通道和流 流的关闭方法中又通道的关闭

        fileInputStream.close();
        fileOutputStream.close();
    }
}
