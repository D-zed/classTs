package naixue.four.naixue.vip.p6.jvm.myclassloader;

import lombok.SneakyThrows;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class MyClassLoader extends ClassLoader{

    private String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    public MyClassLoader() {
    }

    @SneakyThrows
    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes = loadByte(name);

        return defineClass(name,bytes,0,bytes.length);
    }

    private byte[] loadByte1(String name)throws  Exception{
        name = name.replaceAll("\\.", "/");
        FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
        FileChannel channel = fis.getChannel();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        WritableByteChannel writableByteChannel = Channels.newChannel(byteArrayOutputStream);
        ByteBuffer by = ByteBuffer.allocate(1024);

        while (true){
            int read = channel.read(by);
            if (read==0||read==-1){
                break;
            }
            by.flip();
            writableByteChannel.write(by);
            by.clear();
        }
        fis.close();

        return byteArrayOutputStream.toByteArray();

    }

    private byte[] loadByte(String name){
        FileInputStream fis=null;
        BufferedInputStream bis=null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            name = name.replaceAll("\\.", "/");
             fis = new FileInputStream(classPath + "/" + name + ".class");
             bis = new BufferedInputStream(fis);

            byte [] b=new byte[1024];
            int i=0;
            while ((i=bis.read(b))!=-1){
                bos.write(b,0,i);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                assert bis != null;
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bos.toByteArray();
    }


}