package hanshunping.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 视频19
 * 将数据写入buffer时可以采用buffer数组 依次写入
 *
 * Scattering 散射 将数据写入buffer 采用数组依次写
 * Gathering 搜集  buffer 依次读
 * @author dengzidi
 */
public class ScatteringAndGatheringDemo {

    public static void main(String[] args) throws Exception {
        //是哟个serverSocketChannel
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(5555);
        //绑定端口到socket 并启动
        serverSocketChannel.socket().bind(inetSocketAddress);

         ByteBuffer [] byteBuffer = new ByteBuffer[2];
         byteBuffer[0] = ByteBuffer.allocate(2);
         byteBuffer[1] = ByteBuffer.allocate(5);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true){
            int byteRead=0;
        //    while (byteRead<)
        }

    }

}
