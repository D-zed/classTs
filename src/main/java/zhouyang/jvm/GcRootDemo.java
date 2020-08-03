package zhouyang.jvm;

import java.util.concurrent.TimeUnit;

public class GcRootDemo {
    /**
     *
     *  也即是方法中的局部变量
     *    虚拟机栈（栈帧中的本地变量表）中引用的对象
     *
     * 方法区中的变量可以作为gcroot
     *    方法区中类静态属性引用的对象
     *
     *    方法区中常量引用的对象
     *  本地方法中的变量
     *  本地方法栈中JNI（即一般说的Native方法）引用的对象  例如线程这种本地方法
     *
     *
     */

    public static void main(String[] args) throws InterruptedException {
        System.out.println("dev");
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
