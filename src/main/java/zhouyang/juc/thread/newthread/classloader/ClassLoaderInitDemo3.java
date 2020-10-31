package zhouyang.juc.thread.newthread.classloader;

/**
 * 类加载初始化阶段
 * 因为 clinit 方法中包含了所有的类变量的复制动作 和静态语句的执行动作 并且是按照代码顺序进行的 所以
 * 这种静态块是不可以的
 *
 * 而且clinit 总能保证弗雷德clinit方法最先执行
 *
 * 且clinit方法自身就是有同步语义
 * @author dzd
 */
public class ClassLoaderInitDemo3 {
/*
    这个放在前边无法通过的
    static{
        System.out.println(x);
    }
    */
    private static int x=10;
}
