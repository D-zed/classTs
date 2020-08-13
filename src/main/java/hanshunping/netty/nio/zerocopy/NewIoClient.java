package hanshunping.netty.nio.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 这个案例主要是单一的发送 零拷贝发送 但是有上限的只能8M
 */
public class NewIoClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        //客户端代码
        try {
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
            FileInputStream fileInputStream = new FileInputStream("E:\\oss-browser-win32-x64.zip");
            FileChannel fileChannel=fileInputStream.getChannel();
            //  FileChannel fileChannel = FileChannel.open(Paths.get("E:\\oss-browser-win32-x64.zip"), StandardOpenOption.READ);
            //这个再windows上最多智能8m
            fileChannel.transferTo(0, fileChannel.size(), socketChannel);
            fileChannel.close();
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
