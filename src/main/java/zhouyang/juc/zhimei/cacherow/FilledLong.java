package zhouyang.juc.zhimei.cacherow;

import sun.misc.Contended;

/**
 * 将此对象进行填充使其大小变成64字节
 * 7个long类型的字段占用56字节，加上对象头8字节，最终为64字节
 * java8提供了一个注解 @sun.misc.Contended 加上注解之后则对象则填充成和缓存行一样大小
 * 其也可以用来修饰变量，比如Thread中有很多使用此修饰
 * erqie这个注解默认给rt包下的类使用，用户类路径如果要使用这个注解需要添加jvm参数
 * @author dzd
 */
@Contended
public class FilledLong extends Thread{
    public volatile long value=0L;
    public long p1,p2,p3,p4,p5,p6;
}
