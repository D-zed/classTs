package zhouyang.thread.newthread;

/**
 * 线程优先级
 * 默认优先级 5
 * 子线程继承优先级
 * @author dzd
 */
public class PriorityDemo {

    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println(thread.getPriority());
        System.out.println(thread.getId());

    }
}
