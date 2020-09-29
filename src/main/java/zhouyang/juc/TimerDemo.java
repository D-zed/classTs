package zhouyang.juc;

/**
 * TaskQueue 是一个由平衡二叉树实现的优先队列 每个timer对象内部都有一个taskQueue 队列，用户线程调用Timer 的schedule方法就是把timerTask任务添加到TaskQueue
 * 调用shcedule的 delay参数是用来指明该任务的延迟时间
 * TimeThread是具体执行任务的线程，他从TaskQueue队列里面获取优先级最高的任务执行，只有执行了当前的任务才会从任务队列里获取下一个任务，不论是否达到了时间
 * 一个Timer只有一个线程 所以其是一个多生产单消费的模型
 * 正是因为Timer中只有一个消费线程所以 当我们的任何一个任务的异常都会导致其最终的终止 也即是其他的任务也就无法执行了，so使用Timer的定时任务的时候最好对其进行catch
 *
 * 然而 ScheduledThreadPoolExecutor的其他任务不受抛出异常的影响，因为其内部使用了 Catch so日常开发中如果有定时的需求则应该优先使用 ScheduledThreadPoolExecutor
 * @author dzd
 */
public class TimerDemo {

    public static void main(String[] args) {

    }

}
