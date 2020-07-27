package zhouyang.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class VolatileDemo3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //杨哥讲解指令重排
        //类比一张卷子我们选择先把简单的做了再做难的并不影响我们最终把卷子做完
        //然而只是单线程模式下

        //volatile的写会加内存屏障禁止此变量的前后操作重排
        //然而指定重拍的时候一定会按照数据的依赖性来的
    }

    class ReSortSeqDemo{

        int a=0;

        //加上这个关键字则不会指令重排了
       volatile boolean flag=false;

        public void method01(){
            //由于这两个赋值语句在单线程情况下不存在依赖关系，可能会重拍
            a=1;
            flag=true;
        }

        public void method02(){
            if (flag){
                  a=a+5;
                System.out.println(5);
            }
        }
    }
}
