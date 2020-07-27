package zhouyang.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyTask myTask=new MyTask(0,100);

        //这个是一个线程池  和ThreadPoolExecutor继承自同一个  分开合并线程池
        ForkJoinPool forkJoinPool=new ForkJoinPool();

        ForkJoinTask<Integer> submit = forkJoinPool.submit(myTask);
        Integer integer = submit.get();
        System.out.println(integer);
        forkJoinPool.shutdown();

    }
}
//只有抽象方法必须重写  计算  n n+1 n+2 n+3 n+n 的和
//每一个mytask就是一个 fork线程 一直细分下去  然后再join就变成了聚是一团火散是满天星的效果
class MyTask extends RecursiveTask<Integer>{

    private static final Integer ADJUST_VALUE=10;

    public MyTask(){}

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;

    }

    private int begin;
    private int end;

    private int result;

    @Override
    protected Integer compute() {

        if (end-begin<ADJUST_VALUE){
            for (int i = begin; i <=end ; i++) {
                result=result+i;
            }
        }else {
            int medile=(end+begin)/2;
            MyTask myTask1 = new MyTask(begin,medile);
            myTask1.fork();
            MyTask myTask2 = new MyTask(medile+1,end);
            myTask2.fork();
            result=myTask1.join()+myTask2.join();
        }
        return result;
    }
}
