package hanshunping.netty.nio.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 对发送功能的修改
 *
 * @author dzd
 */
public class NewIoClient2 {

    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
            FileChannel fileChannel = FileChannel.open(Paths.get("E:\\oss-browser-win32-x64.zip"), StandardOpenOption.READ);
            System.out.println("文件大小" + fileChannel.size());
            long size = fileChannel.size();
            long postion = 0;
            while (size > 0) {
                long count = fileChannel.transferTo(postion, size, socketChannel);
                postion += count;
                size -= count;
            }
            fileChannel.close();
            socketChannel.close();
            System.out.println("客户端文件传输时间：" + (System.currentTimeMillis() - start));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
