package zhouyang.fanxing.fanxingclass;

import lombok.Data;

/**
 * 泛型类父类
 * 泛型类：顾名思义就是此泛型的传参方式与类相关所以定义在类上，在对象初始化的时候指定具体类型
 * @author dzd
 */
@Data
public class ParentC<T> {

    private T body;

    private String name;
}
