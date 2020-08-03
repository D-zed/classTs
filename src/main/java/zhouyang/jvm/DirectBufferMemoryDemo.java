package zhouyang.jvm;

import java.util.concurrent.TimeUnit;

/**
 * 直接内存溢出的错误这个一般是nio引起的
 * allocateDirect 分配直接内存也就是堆外内存 不属于gc范围内，可能造成jvm内存充足，但是本地内存不充足了
 * allocate 分配内存需要再jvm和native中拷贝所以性能小于上面那种
 * @author dengzidi
 */
public class DirectBufferMemoryDemo {

    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
