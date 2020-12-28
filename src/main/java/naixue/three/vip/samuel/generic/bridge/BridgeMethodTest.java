package naixue.three.vip.samuel.generic.bridge;

import java.lang.reflect.Method;

public class BridgeMethodTest {

    public static void main(String[] args) throws Exception {

        SuperClass<String> subClass = new SubClass();
        subClass.m01("123acddd");  // 1?2
      //  subClass.m01(new Object());      // 1?2?

        Method m01 = subClass.getClass().getDeclaredMethod("m01", Object.class);
        System.out.println(m01.isBridge());
    }
}
