package zhouyang.juc;

import javax.print.DocFlavor;

/**
 * 缓存行
 * 我们的内存与缓存的数据交换单位就是缓存行
 * 所以说当cpu要访问的变量没有在缓存行中就会将其缓存到缓存行中
 * 然而缓存行是有一定的大小的，且每次会将 一个缓存行填满
 * 这样就可能出现多个变量被缓存进入了一个缓存行，这样多个线程
 * 对多个变量操作的时候其实是对缓存行的竞争 所以这也就是伪共享的
 * 概念
 *
 * 为了避免伪共享 java中使用了填充法
 * sum.misc.Contended 比如FileLong将对象填充成64字节
 * 对象头有8个字节
 * @author dzd
 */
public class CacheRowDemo {

    static final int LINE_NUM=1024;
    static final int COLUM_NUM=1024;

    public static void main(String[] args) {
      long [][] array=  new long[LINE_NUM][COLUM_NUM];
      long start=System.currentTimeMillis();
        for (int i = 0; i < LINE_NUM; i++) {
            for (int j = 0; j < COLUM_NUM; j++) {
                //这种按行读的充分使用了缓存行
                array[i][j]=i+2+j;
            }
        }
        System.out.println("cache time "+(System.currentTimeMillis()-start));

        //此种上下读取的没有用到缓存行
       /* long start=System.currentTimeMillis();
        for (int i = 0; i < LINE_NUM; i++) {
            for (int j = 0; j < COLUM_NUM; j++) {
                array[j][i]=i+2+j;
            }
        }
        System.out.println("not cache time "+(System.currentTimeMillis()-start));*/
    }

}
