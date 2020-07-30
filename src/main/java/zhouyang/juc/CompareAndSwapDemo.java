package zhouyang.juc;



import java.util.concurrent.atomic.AtomicInteger;

/**
 * cas原子类的缺点
 * 只能保证一个共享变量的原子操作
 * 修改线程并发高的时候cpu性能消耗太大，因为自选
 * ABA问题 一个线程中读的数据和预期数据一样就说明他真的是预期值吗，
 * 不一定，因为预期值在中间可能变成了B又在我们获取的时候变成了A
 * 但是B的时候是无感知的
 *
 * 有个哥们说aba问题值修改没问题 引用修改有问题
 */
public class CompareAndSwapDemo {

    public static void main(String[] args) {
        //这个可以用
        AtomicInteger atomicInteger=new AtomicInteger(5);
        //底层调用的是sun.misc 下的unsafe类，这个是jdk自带的属于jdk中的
        //其中都是是native方法，并且可以直接操作内存 其中内存偏移量记录在对应的原子类中 valueOffset
        atomicInteger.compareAndSet(5,23);
        System.out.println(atomicInteger.intValue());
        atomicInteger.compareAndSet(5,6);
        System.out.println(atomicInteger);

        //用通俗的话描述就是cas底层操作通过比较当前工作内存的值和主内存的值相比较，如果相同了代表符合预期则修改
        //如果不相等说明不符合预期，则不修改，并且重新在主内存中读取当前值，重复以上操作，直到修改成功
        int andIncrement = atomicInteger.getAndIncrement();
        System.out.println(andIncrement);
        //cas是一条cup并发原语是cpu的一个原子指令，基于cpu底层控制不会造成数据问题
        //Unsafe
    }
}