package zhouyang.juc.thread.newthread.classloader;

/**
 * 类加载器的介绍
 * BootStrapClassLoader
 * @author dzd
 */
public class ClassLoaderDemo4 {

    public static void main(String[] args) {
        System.out.println("Bootstrap "+String.class.getClassLoader());

        System.out.println("bootstrap类加载器的目录");
        System.out.println(System.getProperty("sun.boot.class.path"));

        System.out.println("扩展类加载器");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println("applicationClassLoader");
        System.out.println(ClassLoaderDemo4.class.getClassLoader());
    }

}