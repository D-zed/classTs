package naixue.three.vip.samuel.anno;

/**
 * 1、注解本质就是 extends java.lang.annotation.Annotation 的一个interface
 * 2、注解的属性：在注解中定义的抽象方法叫做注解的属性
 * 属性的返回值必须是
 * String
 * 基本数据类型
 * Enum
 * 注解类型
 * 以上四种类型的数组类型
 * 3、元注解 描述注解的注解
 *   @Retention 声明周期 SOURCE<CLASS<RUNTIME
 *   @Target   当前注解可以作用的范围 Type 类 Field
 *   @Docment javadoc 时是否被包含
 *   @Inherited 是否允许被子类继承
 * 4、java8注解增加了改进 可重复注解、用户类型的注解
 * 5、注解的应用：注解处理器
 *    APT(Annotation Proceessing Tool) javac的一个工具，编译时扫描和处理注解用的
 *    javax.lang.*
 */
public @interface MyAnno {

    String showStr();
    int showInt() default 10;
    String[] showStrArr();
    ColorEnum showEnum();
    MyAnno2 showAnno();
}
