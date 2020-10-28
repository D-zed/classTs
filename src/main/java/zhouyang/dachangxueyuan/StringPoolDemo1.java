package zhouyang.dachangxueyuan;

/**
 * 由于运行时常量池是方法区的一部分 元空间进行方法区的实现
 * intern是一个本地方法，它的作用是如果字符串常量池中已经包含一个等于此String对象的字符串则返回池中代表这个字符串对象的引用。 否则将此对象包含的字符串添加到常量池之中并且返回并将此String对象的引用返回
 * 再返回此String的引用
 *
 * 深入理解jvm pdf98页
 * 提到由于高版本的虚拟机已经没有了永久代的概念并且字符串常量池已经移到java堆中  所以intern就只记录字符串首次出现的引用
 * intern()
 * 也就是说intern满足一个首次遇见原则
 *
 * 深入理解jvm pdf 77页
 * jdk7已经把原本放在永久代的字符串常量池静态变量等移除，而到了jdk8 终于完全废弃了永久代的概念 完全由元空间替代
 *
 * 深入理解jvm pdf 78页
 * 运行时常量池是方法区的一部分 常量池中存放各种编译期生成的各种字面量和符号引用（其实直接引用也会放到这里） 类加载之后存放到运行时常量池
 * @author dzd
 */
public class StringPoolDemo1 {

    public static void main(String[] args) {
        String sd = "taobao";

        System.out.println(sd);
        System.out.println(sd.intern());
        System.out.println(sd==sd.intern());


        String s = new StringBuilder("ali").append("baba").toString();
        System.out.println(s);
        System.out.println(s.intern());
        System.out.println(s==s.intern());

        //因为java rt包下加载的时候就有java的关键字加载进来了 所以此时 ss.intern的指向是更早一些的java字符串的引用
        String ss = new StringBuilder("ja").append("va").toString();
        System.out.println(ss);
        System.out.println(ss.intern());
        System.out.println(ss==ss.intern());

    }
}
