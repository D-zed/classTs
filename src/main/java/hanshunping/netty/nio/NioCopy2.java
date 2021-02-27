package hanshunping.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用niocopy
 * @author dzd
 */
public class NioCopy2 {

    String classPath;
    public NioCopy2(String classPath) {
        this.classPath=classPath;
    }

    public void copy(String name,String targetName) throws IOException {
        FileInputStream fis = new FileInputStream(classPath + "/" + name );
        FileChannel fisChannel = fis.getChannel();

        FileOutputStream fos = new FileOutputStream(classPath + "/" + targetName );
        FileChannel fosChannel = fos.getChannel();

        ByteBuffer by = ByteBuffer.allocate(1024);

        while (true){
            int read = fisChannel.read(by);
            if (read==0||read==-1){
                break;
            }
            by.flip();
            fosChannel.write(by);
            by.clear();
        }
        fosChannel.close();
        fos.close();
        fisChannel.close();
        fis.close();

    }

    public static void main(String[] args) throws IOException {
        NioCopy1 nioCopy1 = new NioCopy1("F:\\myClassLoader\\zhouyang\\juc\\thread\\newthread\\classloader");
        nioCopy1.copy("HelloWorld.java","HelloWorld111.java");

    }
}
