package naixue.four.naixue.vip.p6.jvm.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class DirectMemoryTest {
    public static int _1MB=1024*1024;

    //堆外内存溢出
    //不要执行，会死机
    //-Xmx20M -XX:MaxDirectMemorySize=10M
    public static void main(String[] args) throws Exception{
        Field unsafeField= Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe =(Unsafe) unsafeField.get(null);
        while (true){
            unsafe.allocateMemory(_1MB);
        }
    }
}
