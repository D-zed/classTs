package naixue.six.lock;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * sleep的位置不同那么偏向锁是否开启也不同
 * java object layout
 */
public class JOLDemo {
    public static void main(String[] args) throws InterruptedException {
//        Thread.sleep(5000);
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
//        Thread.sleep(5000);
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
