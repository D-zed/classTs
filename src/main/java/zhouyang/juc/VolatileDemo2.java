package zhouyang.juc;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo2 {


    //证明volatile的非原子性
    public static void main(String[] args) {

        //一个线程+2000次 开十个线程
        MyData1 myData = new MyData1();
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 1; j <=1000; j++) {
                    myData.add();
                    myData.addAtomic();
                }

            },"A").start();
        }
        //当计算完成之后获取计算结果
        while (Thread.activeCount()>2){
            //交出当前线程执行权
            Thread.yield();
        }


        System.out.println( myData.number);
        System.out.println(myData.num);
    }
}
class MyData1{

     AtomicInteger number=new AtomicInteger(0);

     int num=0;
    public void addAtomic(){
        number.incrementAndGet();
    }

    public void add() {
        num++;
    }
}