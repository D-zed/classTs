package hanshunping.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class NioCopy1 {
    String classPath;
    public NioCopy1(String classPath) {
        this.classPath=classPath;
    }

    public void copy(String name,String targetName) throws IOException {
        FileInputStream fis = new FileInputStream(classPath + "/" + name );
        FileChannel fisChannel = fis.getChannel();

        FileOutputStream fos = new FileOutputStream(classPath + "/" + targetName );
        FileChannel fosChannel = fos.getChannel();

        fisChannel.transferTo(0,fisChannel.size(),fosChannel);
        fosChannel.close();
        fos.close();
        fisChannel.close();
        fis.close();
    }

    public static void main(String[] args) throws IOException {
        NioCopy1 nioCopy1 = new NioCopy1("F:\\myClassLoader\\zhouyang\\juc\\thread\\newthread\\classloader");
        nioCopy1.copy("HelloWorld.java","HelloWorld11.java");

    }
}
