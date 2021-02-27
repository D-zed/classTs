package naixue.four.naixue.vip.p6.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 自定义类加载器  实现findClass java高并发编程详解 多线程与架构设计 162页
 * @author dzd
 */
public class MyClassLoader5 extends ClassLoader{

    private final static Path DEFULT_CLASS_DIR= Paths.get("F:","myClassLoader");
    private final Path classDir;

    /**
     * 使用默认的class路径
     */
    public MyClassLoader5(){
        super();
        this.classDir=DEFULT_CLASS_DIR;
    }

    /**
     * 使用指定的class路径
     * @param classDir
     */
    public MyClassLoader5(String classDir){
        super();
        this.classDir=Paths.get(classDir);
    }

    /**
     * 指定dir和 父类加载器
     * @param classDir
     * @param parent
     */
    public MyClassLoader5(String classDir,ClassLoader parent){
        super(parent);
        this.classDir=Paths.get(classDir);
    }

    /**
     * 指定父类加载器
     * @param parent
     */
    public MyClassLoader5(ClassLoader parent){
        super(parent);
        this.classDir=DEFULT_CLASS_DIR;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] classBytes = readClassBytes(name);

        if (null==classBytes || classBytes.length==0){
            throw new ClassNotFoundException("类没加载成功");
        }

        return this.defineClass(name,classBytes,0,classBytes.length);
    }

    private byte[] readClassBytes(String name) throws ClassNotFoundException {
        String classPath = name.replace(".", "/");
        //全路径
        Path classFullPath = classDir.resolve(Paths.get(classPath+".class"));
        if (!classFullPath.toFile().exists()){
           throw new ClassNotFoundException("the class not found");
        }

        try(ByteArrayOutputStream baos = new ByteArrayOutputStream())
        {
            Files.copy(classFullPath,baos);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException("对应的class没找到");
        }
    }

    @Override
    public String toString() {
        return "my classLoader";
    }
}