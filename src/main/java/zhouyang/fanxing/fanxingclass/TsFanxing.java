package zhouyang.fanxing.fanxingclass;

/**
 * 泛型 类基本语法
 * class 类名称 <泛型标识，泛型标识，…> {
 *   private 泛型标识 变量名;
 *   .....
 * }
 * 测试泛型类
 * @author ASUS
 */
public class TsFanxing {

    public static void main(String[] args) {
        ChildC childC = new ChildC();
        childC.setBody("我是字符串");
        ChildC2<Integer> integerChildC2 = new ChildC2<>();
        integerChildC2.setBody(1111);

    }

}
