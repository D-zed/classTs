package zhouyang.thread.newthread;

/**
 * 暂时了解 实战感觉意义不大
 * 顾名思义 就是我们讲的线程组 可以对我们的线程按组操作 默认的线程组是父线程组
 * 可以对组做操作 设置 daemon 设置interrupt 等操作
 * @author dzd
 */
public class ThreadGroupDemo {

    public static void main(String[] args) {

        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup group1 = new ThreadGroup("Group1");
        System.out.println(group1.getParent()==threadGroup);
        ThreadGroup group=new ThreadGroup(group1,"group2");
        System.out.println(group.getParent()==group1);
    }
}