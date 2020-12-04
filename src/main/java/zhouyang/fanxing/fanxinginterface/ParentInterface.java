package zhouyang.fanxing.fanxinginterface;

/**
 * 泛型接口父
 * 与泛型父类规则一样
 * 实现类不是泛型类，接口要明确数据类型
 * 实现类也是泛型类，实现类和接口的泛型类型要一致
 *
 * 然而因为接口中只能是静态常量所以一定是类型明确并且有初始值的
 * @author dzd
 */
public interface ParentInterface<T> {

    T getData();
}
