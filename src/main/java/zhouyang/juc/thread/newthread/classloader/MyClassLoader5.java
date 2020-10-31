package zhouyang.juc.thread.newthread.classloader;

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

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        this.

        return super.findClass(name);
    }

    private byte[] readClassBytes(String name){
        String classPath = name.replace(".", "/");
        classDir.resolve(Paths.get(classPath));
    }
}