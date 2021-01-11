package zhouyang.jvm.basic;

import sun.misc.VM;

/**
 * 堆 栈 程序计数器 方法区 本地方法栈
 * 1.8还包括了jvm外的java内存 元数据区和 直接内存
 * https://blog.csdn.net/qzqanzc/article/details/81008598
 *
 * 类装载器
 * 类加载器加载的class文件加载到方法去中 加载的内容相当于模板 然后根据这个模板可以构造出来很多实例
 * 类加载器分为 bootstrap classloader c++写的
 *            ext 类加载器
 *            application 类加载器
 *
 *
 *  双亲委派机制（我爸是李刚，有事找我爹）和 沙箱安全规则（双亲委派保证沙箱安全 看 java.lang.String）
 *
 *  pc寄存器（程序计数器） 线程私有的每个线程单独有 记录了当前线程执行字节码的行号
 *  方法区
 *     存储了一个类的结构信息（类似与一个模板用这个模板可以产生实例）
 *     方法区是规范，在不同的虚拟机里实现不一样 ，典型的就是永久代和元空间
 *
 *  栈管运行 堆管存储
 *
 *  栈也是有栈内存的 且其生命周期与线程生命周期等同
 *     且八种基本类型变量 +对象的引用变量 + 实例方法都是在函数的占内存中分配
 *     且每一个方法的执行 会被封装成栈帧 进行压栈
 *     栈中具体保存 局部变量表 （局部变量就是方法的本地变量 方法形参 ），操作数栈运行时常量池的引用，方法返回地址（出口），动态链接
 * @author dengzidi
 */
public class JVMBasic1 {

    public static void main(String[] args) {

        Object o=new Object();
        //获取当前对象的class类的类加载器
        //Object是java自带的 此时打印的是null因为其是bootstrap 加载器所以无法得到
        //此类加载器的作用域 jre/lib
        System.out.println(o.getClass().getClassLoader());
        MyObject myObject = new MyObject();
        //这个加载我们自定义的类所以使用的是applicationclassloader
        System.out.println(myObject.getClass().getClassLoader());
        //看看他们的父加载器都是谁
        System.out.println(myObject.getClass().getClassLoader());
        System.out.println(myObject.getClass().getClassLoader().getParent());
        System.out.println(myObject.getClass().getClassLoader().getParent().getParent());

        //extclassloader加载的是jre/lib/ext 下的类
    }
}
class MyObject{}
