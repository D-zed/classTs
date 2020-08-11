package hanshunping.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * nio的客户端
 * @author dzd
 */
public class NioClientDemo2 {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.configureBlocking(Boolean.FALSE);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",2222);

        if (!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("因为连接需要时间，客户端不会阻塞");
            }
        }

        String str="1234567890";
        //根据内容创建buffer
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        socketChannel.write(buffer);
        System.in.read();
    }
}
