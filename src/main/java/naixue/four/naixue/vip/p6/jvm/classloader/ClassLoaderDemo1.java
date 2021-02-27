package naixue.four.naixue.vip.p6.jvm.classloader;

/**
 * 类加载器
 * 加载阶段 加载二进制文件 也就是类的class文件
 * 连接阶段
 *     验证确保类文件的正确性，比如class版本 魔数
 *     准备 为类的静态变量分配内存并且为其初始化默认值
 *     解析 把类中的符号引用转为直接引用
 * 初始化阶段
 *     为类的静态变量赋予正确地初始值
 *
 *  1.最常规的使用new关键字 可以出发类的加载
 *  2.调用静态属性也可以触发类的加载
 *  3.调用静态方法可以触发类的加载
 *  4.反射加载也可以
 *
 *  其他的 被动使用 无法初始化
 *  1.然而使用某个类型构造数组的时候不会导致类加载
 *  2.引用类的静态常量不会使类加载
 *
 *  总结 类的加载就是将class文件中的二进制数据读取到内存之中 然后将该字节流所代表的静态存储结构转换为方法区中运行时的数据结构
 *  并且在堆内存中生成一个该类的 class对象，作为访问方法区数据结构的入口 书中149页图所示
 * @author dzd
 */
public class ClassLoaderDemo1 {

    public static void main(String[] args) {

        //证明创建数组不会初始化
        Simple[] simples = new Simple[10];
        System.out.println(simples.length);
       // Simple simple=new Simple();
        //引用静态常量没初始化
        System.out.println(Simple.MAX);
        //引用 静态属性则会初始化 因为静态变量的赋值在类加载的初始化阶段
        System.out.println(Simple.MAX2);

    }
}

class Simple{
    //引用类的静态变量不会初始化
    public static final int MAX=100;
    public static int MAX2=1000;

    static {
        System.out.println("用我创建个数组会初始化么");
    }
}
