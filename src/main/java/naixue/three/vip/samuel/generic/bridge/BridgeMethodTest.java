package naixue.three.vip.samuel.generic.bridge;

import java.lang.reflect.Method;

/**
 * 当我们的子类实现中对父类的泛型进行的限制则会生成一个对应的桥接方法
 * 使用javap -verbose 可以看到对应的桥接方法（也就是执行过程中的泛型擦除）
 *  public java.lang.Object m01(java.lang.Object);
 *     descriptor: (Ljava/lang/Object;)Ljava/lang/Object;
 *     flags: ACC_PUBLIC, ACC_BRIDGE, ACC_SYNTHETIC
 *     Code:
 *       stack=2, locals=2, args_size=2
 *          0: aload_0
 *          1: aload_1
 *          2: checkcast     #7                  // class java/lang/String
 *          5: invokevirtual #8                  // Method m01:(Ljava/lang/String;)Ljava/lang/String;
 *          8: areturn
 *       LineNumberTable:
 * @author dzd
 */
public class BridgeMethodTest {

    public static void main(String[] args) throws Exception {

        //SuperClass subClass = new SubClass();
        //为了是我们的泛型更加安全尽可能使用的时候就进行限定，将不合理在编译时刻就暴漏出来
        SuperClass<String> subClass = new SubClass();
        subClass.m01("123acddd");  // 1?2
        //此处因为传参为obj所以走的是桥接方法，然而桥接方法中会对返回值进行泛型校验，也就是将其obj值强转，所以执行的时候会报错
        //java.lang.Object cannot be cast to java.lang.String
        //subClass.m01(new Object());      // 1?2?
        //
        Method m01 = subClass.getClass().getDeclaredMethod("m01", Object.class);
        //此方法为判断走的是否为桥接方法
        System.out.println(m01.isBridge());

    }
}
