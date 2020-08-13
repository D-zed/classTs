package hanshunping.netty.nio.zerocopy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewIoServer {

    public static void main(String[] args) throws IOException {
        SocketChannel acceptChannel = null;
        FileChannel fileChannel = null;
        try {
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            socketChannel.bind(new InetSocketAddress("127.0.0.1", 9999));
            while (true) {
                acceptChannel = socketChannel.accept();
                long start = System.currentTimeMillis();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                FileOutputStream fileOutputStream = new FileOutputStream("F:\\oss-browser-win32-x64.zip");
                fileChannel = fileOutputStream.getChannel();
                //fileChannel = FileChannel.open(Paths.get("F:/oss-browser-win32-x64.zip"), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
                while (acceptChannel.read(buffer) != -1) {
                    //todo 这个地方我的bug是没有flip改变模式所以buffer就没有写到对应的位置
                    buffer.flip();
                    fileChannel.write(buffer);
                    buffer.clear();
                }
                System.out.println("写入时间是：" + (System.currentTimeMillis() - start));
                acceptChannel.close();
                fileChannel.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
