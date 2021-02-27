package hanshunping.netty.bio;

import hanshunping.netty.nio.NioCopy1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author dzd
 */
public class BioCopy1 {

    String classPath;

    public BioCopy1(String classPath) {
        this.classPath = classPath;
    }

    public void copy(String name, String targetName) throws IOException {
        FileInputStream fis = new FileInputStream(classPath + "/" + name);

        FileOutputStream fos = new FileOutputStream(classPath + "/" + targetName);

        byte[] b = new byte[1024];
        int len = 0;
        while ((len = fis.read(b)) != -1) {
            fos.write(b, 0, len);
        }
        fos.flush();
        fos.close();
        fis.close();

    }

    public static void main(String[] args) throws IOException {
        NioCopy1 nioCopy1 = new NioCopy1("F:\\myClassLoader\\zhouyang\\juc\\thread\\newthread\\classloader");
        nioCopy1.copy("HelloWorld.java", "HelloWorldbio.java");

    }

}