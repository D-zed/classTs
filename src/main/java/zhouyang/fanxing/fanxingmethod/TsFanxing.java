package zhouyang.fanxing.fanxingmethod;

/**
 * 泛型方法
 * 泛型方法，是在调用方法的时候指明泛型的具体类型
 * 修饰符 <T, E, ...> 返回值类型 方法名(形参列表){}
 * 总之<>尖括号加T表示声明的含义
 *
 *   修饰符与返回值类型之间的 <T> 用于声明此方法为泛型方法
 *   只有声明了 <T> 的方法才是泛型方法，就算返回值类型中的泛型类使用泛型的成员方法也并不是泛型方法
 *   <T> 表明该方法将使用泛型类型 T，此时才可以在方法中使用泛型类型 T
 *
 *   如果 静态（static） 方法 要使用泛型能力，就必须使其成为泛型方法
 * @author dzd
 */
public class TsFanxing {

    public static void main(String[] args) {
        //泛型方法可能就这个用处了吧
        //泛型仅仅与方法有关，且方法中的参数类型在传参的时候传入
        ParentM parentM=new ParentM();
        parentM.getA("aaa",1,"aaa",Boolean.TRUE);

        ParentM.getB("aaa",1,"aaa",Boolean.TRUE);
    }

}
