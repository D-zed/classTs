package hanshunping.netty.nio.zerocopy;

/**
 * 零拷贝知识
 * 传统io的一次数据读写
 * 最基础的拷贝是 dma到内核缓冲区，然后内核缓冲区到用户缓冲区并且进入了用户态 在将拷贝到 socketbuffer 再一次切换到内核态，最后
 * 将数据拷贝到协议栈（一共三次切换状态，四次内存拷贝）
 *
 *
 * 1 mmap 这个是内存映射版本的数据读写 dma拷贝到内核缓冲区，然后切换到用户态，这也即使内存映射，用户态直接操作内核缓冲区的数据
 * 将其copy到socketbuffer 然后再copy到协议栈 也就比以上的少了一部拷贝
 *
 * 2 linux 2.4版本的sendFile函数就是一个 加强版本的零拷贝了 它可以直接跳过了用户态，也就不需要拷贝到用户缓冲区了
 * 并且也做到了内核缓冲区的数据只有一份
 * dma到内核缓冲区 内核缓冲区直接到协议栈 两次拷贝 并且根本不需要通过用户态所以减少了状态切换的性能消耗
 *
 * 这个不错
 * https://blog.csdn.net/weixin_30402085/article/details/99029292?utm_medium=distribute.pc_relevant.none-task-blog-baidulandingword-7&spm=1001.2101.3001.4242
 * @author ASUS
 */
public class ZeroCopyDemo {
}
