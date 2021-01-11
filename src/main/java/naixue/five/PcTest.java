package naixue.five;

/**
 * 使用cms+parnew辣鸡收集器    打印出来当前jvm配置               打印gc日志              gc日志输出位置              最大存活次数
 * -XX:+UseConcMarkSweepGC  -XX:+PrintCommandLineFlags -XX:+PrintGCDetails  -Xloggc:garbage-collection-cms.log  -XX:MaxTenuringThreshold=14
 * -XX:+UseParallelGC       -XX:+PrintCommandLineFlags -XX:+PrintGCDetails  -Xloggc:garbage-collection-parallel.log  -XX:MaxTenuringThreshold=14
 * -XX:+UseParNewGC         -XX:+PrintCommandLineFlags -XX:+PrintGCDetails  -Xloggc:garbage-collection-parnew.log  -XX:MaxTenuringThreshold=14
 * -XX:+UseSerialGC -XX:+PrintCommandLineFlags -XX:+PrintGCDetails  -Xloggc:garbage-collection-seria.log  -XX:MaxTenuringThreshold=14
 * -XX:+UseG1GC -XX:+PrintCommandLineFlags -XX:+PrintGCDetails  -Xloggc:garbage-collection-g1.log  -XX:MaxTenuringThreshold=14
 *
 * @author dzd
 */
public class PcTest {

    public static void main(String[] args) {
        ProducterAndConsumer producterAndConsumer = new ProducterAndConsumer();
        ProducterAndConsumer2 producterAndConsumer2 = new ProducterAndConsumer2();

        Thread product = new Thread(()->{
            while (true){
                producterAndConsumer.product(1);
            }
        });

        Thread consume = new Thread(()->{
            while (true){
                producterAndConsumer.consume(1);
            }
        });

        product.start();
        consume.start();

         /* Thread product = new   Thread(()->{
            while (true){
                producterAndConsumer2.product(1000);
            }
        });

        Thread consume = new Thread(()->{
            while (true){
                producterAndConsumer2.consume(1000);
            }
        });

        product.start();
        consume.start();*/
    }
}
