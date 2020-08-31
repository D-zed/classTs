package zhouyang.juc;

import java.util.concurrent.atomic.LongAccumulator;

public class LongAccumulatorDemo {

    public static void main(String[] args) {
        LongAccumulator longAccumulator=new LongAccumulator((left,right)->{
            return left+right;
        },0);

        longAccumulator.accumulate(2);

        System.out.println(longAccumulator);

    }
}
