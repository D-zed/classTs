package naixue.four.naixue.vip.p6.jvm.classloader;

/**
 * 类加载过程的顺序案例 java高并发编程详解_多线程与架构设计 148页
 * 预测 1 1 结果一样
 *
 * 详解类加载过程中的连接部分
 *    验证：验证的内容包括了
 *        1.文件格式验证
 *        魔数咖啡宝贝
 *        class文件的版本号是否兼容当前的jvm
 *        class的md5指纹
 *        常量池中的常量是否存在不被支持的变量类
 *        指向常量池的引用是否指到了不存在的常量
 *        2。元数据的验证
 *        元数据的验证其实是对class的字节流进行语义分析的过程，整个语义分析就是为了确保class字节流符合jvm规范的要求
 *        比如finnal类不可以继承 方法不能重写 重载等等
 *        3.字节码验证
 *        对字节码进一步验证 主要针对程序的控制流程 循环分支
 *        保证当前线程的程序计数器不会跳转到不合法的字节码指令中去
 *        保证类型的转换时合法的，比如用A声明的引用不能用B进行强制类型转换
 *        4.符号引用验证
 *        符号引用的验证目的就是为了保证解析动作的顺利执行
 *        通过符号引用描述的字符串全限定名称是否能够顺利的找到相关的类
 *        符号引用中的类，字段，方法，是否对当前类可见，比如不能访问引用类的私有方法
 *     准备阶段
 *        为静态变量开辟内存赋初值
 *     解析  将符号引用替换成直接引用的过程
 *         类接口的解析
 *         字段的解析
 *         类方法解析
 * 类的初始化阶段
 *      clinit 也就是类加载的最后一步 对静态变量赋值
 *
 *
 * @author dzd
 */
public class ClassLoaderOrder2 {

    private static int x=0;

    private static int y;

    private static ClassLoaderOrder2 instance=new ClassLoaderOrder2();

    private ClassLoaderOrder2(){
        x++;
        y++;
    }
    public static ClassLoaderOrder2 getInstance(){
        return instance;
    }

    //final方法可以重载不可以重写
    public final Simple getdd(){
        return new Simple();
    }

    public final Simple getdd(Simple simple){
        return simple;
    }

    public static void main(String[] args) {
        ClassLoaderOrder2 instance = ClassLoaderOrder2.getInstance();
        System.out.println(instance.x);
        System.out.println(instance.y);
    }

}