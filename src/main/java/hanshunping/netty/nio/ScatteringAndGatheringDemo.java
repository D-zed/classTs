package hanshunping.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * 视频19
 * 将数据写入buffer时可以采用buffer数组 依次写入
 * <p>
 * Scattering 散射 将数据写入buffer 采用数组依次写
 * Gathering 搜集  buffer 依次读
 *
 * @author dengzidi
 */
public class ScatteringAndGatheringDemo {

    public static void main(String[] args) throws Exception {
        //是哟个serverSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(5555);
        //绑定端口到socket 并启动
        serverSocketChannel.socket().bind(inetSocketAddress);

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(3);
        byteBuffers[1] = ByteBuffer.allocate(5);

        SocketChannel socketChannel = serverSocketChannel.accept();

        int messageLength = 8;
        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long read = socketChannel.read(byteBuffers);
                byteRead += read;
                System.out.println("累计读取的字节数+" + byteRead);
                Arrays.asList(byteBuffers).stream()
                        .map(buf -> buf.position() + "--" + buf.limit()).forEach(System.out::println);
            }
            Arrays.asList(byteBuffers).forEach(item -> item.flip());
            long byteWrite = 0;
            while (byteWrite < messageLength) {
                long write = socketChannel.write(byteBuffers);
                byteWrite += write;
                System.out.println("累计写入的字节数" + byteWrite);
            }
            Arrays.asList(byteBuffers).forEach(item -> item.clear());
            System.out.println("byteRead:==" + byteRead + "--byteWrite==:" + byteWrite);
        }
    }

}
