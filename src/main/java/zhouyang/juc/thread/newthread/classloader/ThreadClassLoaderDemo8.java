package zhouyang.juc.thread.newthread.classloader;

/**
 * 线程上下文类加载器
 * 为什么要有线程上下文类加载器 177页
 * 这个与jvm类加载器双亲委派原则有关
 *
 * 线程类加载器模式是系统类加载器 并且可以通过设置与线程相关 这样使用线程类加载其就可以完全无视双亲委派了，也可以打破这个作用域了
 * 并且这也是jdk官方给出的打破双亲委派的方法
 *
 * 好多spi 都是如此设计的 就是因为跟加载器的作用域无法 触及application加载器 所以使用线程类加载器打破 是的 应用程序调->jdk->应用程序
 * @author dzd
 */
public class ThreadClassLoaderDemo8 {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader());
    }

}
