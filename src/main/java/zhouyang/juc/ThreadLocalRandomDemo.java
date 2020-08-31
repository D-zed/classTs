package zhouyang.juc;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Random 获取随机数的时候下面为了保证多线程下的安全使用的是cas操作 使用老seed 种子生成新种子 再更新
 * 如果并发线程多的情况下就会出现问题了造成cas自旋过多，浪费cpu资源
 *
 * 所以 ThreadLocalRandom就出现了
 * @author dzd
 */
public class ThreadLocalRandomDemo {

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {

            System.out.println(random.nextInt(5));
        }


        ThreadLocalRandom current = ThreadLocalRandom.current();
        for (int i = 0; i < 10; i++) {
            System.out.println(current.nextInt(5));
        }
    }
}
