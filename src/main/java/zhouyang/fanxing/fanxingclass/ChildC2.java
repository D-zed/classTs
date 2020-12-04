package zhouyang.fanxing.fanxingclass;

/**
 * 泛型类子类类
 * 泛型类：子类继承带有泛型的父类的时候要不自己本身就是个泛型类，这样子类可以将泛型传递给父类
 * 不然就直接指定父类的泛型即可
 * @author dzd
 */
public class ChildC2<T> extends ParentC<T>{

    private String openId;
}
