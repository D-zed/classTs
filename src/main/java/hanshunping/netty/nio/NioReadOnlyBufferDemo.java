package hanshunping.netty.nio;

import java.nio.ByteBuffer;

/**
 * 只读buffer案例
 * @author dengzidi
 */
public class NioReadOnlyBufferDemo {

    public static void main(String[] args) {
        ByteBuffer allocate = ByteBuffer.allocate(64);
        for (int i = 0; i < 64; i++) {
            allocate.put((byte) i);
        }

        allocate.flip();
        //将buffer设置为只读buffer
        ByteBuffer readOnlyBuffer = allocate.asReadOnlyBuffer();
        //这个类型是 java.nio.HeapByteBufferR
        System.out.println(readOnlyBuffer.getClass());
        while (readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }
        //只读的不能写  java.nio.ReadOnlyBufferException
        readOnlyBuffer.put((byte) 10);


    }
}
