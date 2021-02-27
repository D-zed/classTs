package zhouyang.thread.newthread;

/**
 * synchronized
 * 1.互斥锁
 * 2.synchronized包括两个jvm指令 monitor enter monitor exit 保证了内存可见性，也就是说代码块中的逻辑都强制从主内存获取
 * 3.严格遵守happens-before原则 也即是 monitor enter 指令一定在 monitor exit之前 不会产生指令重排
 * 放到方法上则 锁对象是this 静态方法是 class类
 *
 * 死锁出现的可能
 * 1.交叉锁
 * 2.内存不足 ，引起线程等待其他线程释放资源
 * 3.一问一答式的交互，出现问题导致再也等不到回答 造成死锁
 * 4.死循环假锁
 * @author dzd
 */
public class SynchronizedDemo {
}
