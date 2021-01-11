package zhouyang.jvm.exceptionerror;

import sun.misc.VM;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * 直接内存溢出的错误这个一般是nio引起的
 * allocateDirect 分配直接内存也就是堆外内存 不属于gc范围内，可能造成jvm内存充足，但是本地内存不充足了
 * allocate 分配内存需要再jvm和native中拷贝所以性能小于上面那种
 * jinfo -flag MaxDirectMemorySize 11368 默认是0和栈一样没改过就是0
 *  默认大小是 物理内存的四分之一
 * 直接内存介绍
 *
 * 一下这篇jvm文章不错
 * https://www.jianshu.com/p/17e72bb01bf1?tdsourcetag=s_pcqq_aiomsg
 * 其中讲解到我们的直接内存的引用存储在堆中 并且每次fullgc
 * 的时候就会对cleaner进行清除从而释放直接内存freeMemory
 * 并且当我们执行allocateDirect 分配直接内存的时候
 * 回主动调用 System.gc 创建cleaner对象  这个gc有待考证
 *
 * 使用堆外 直接内存的好处最明显的就是 减少了 gc的压力 因为少了很多对象在其上 只有fullgc之时才会对其清理
 *
 *
 *
 * https://www.jianshu.com/p/2180004229dd
 *
 * @author dengzidi
 */
public class DirectBufferMemoryDemo {

    /**
     * 设置 直接内存的大小让其溢出  -XX:MaxDirectMemorySize=5M
     * @param args
     */
    public static void main(String[] args) {
        //获取最大的直接内存
        long l = VM.maxDirectMemory();
        System.out.println("jvm最大的直接内存 ："+l/(double)1024/1024/1024+"G");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //撑爆直接内存
        ByteBuffer.allocateDirect(6*1024*1024);
    }
}