package zhouyang.juc;

public class ProductConsumerDemo {

    public static void main(String[] args) {

        //思路就是判断 干活 通知

        //模拟交替唤醒，但是当增加多个线程的时候就会出现虚假唤醒的情况，因为wait 的时候会释放锁所以其交互判断一定得用while
        //因为其实你这个时候按理说是唤醒的也该你执行了，但是却不是在原来的位置继续执行，所以可能依然存在问题，并且官方文档
        //里边的例子也特别提到了这一点
        ProductC productC = new ProductC();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    productC.product();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    productC.consumer();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        },"B").start();
    }

}

//高内聚将功能聚到一个类中 低耦合，此类与另一个类无关 线程是线程 对象是对象
class ProductC{

   private  Integer count=0;

    public  synchronized void product() throws Exception {

        //这个wait是一种错误的写法因为当我们的wait可能有几个线程同时wait在这里然后突然一个notify出现使得多个线程都唤醒了，然后继续执行，
        //但是此时就无法判断是否满足判断条件了，如果不满足则也会生产就造成了一种虚假唤醒，就是说这个线程本不该唤醒
        //比如当多个生产者多个消费者的时候这种问题就很明显
        //所以wait的时候必须使用while 循环判断才可以
        if (count!=0){
            this.wait();
        }
        count++;
        System.out.println(Thread.currentThread().getName()+"---"+count);
        this.notifyAll();
    }

    public  synchronized void consumer() throws Exception {

        if (count==0){

            this.wait();
        }
        count--;
        System.out.println(Thread.currentThread().getName()+"---"+count);
        this.notifyAll();
    }
}
