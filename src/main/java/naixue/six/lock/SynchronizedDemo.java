package naixue.six.lock;

public class SynchronizedDemo {
    public void method() {
        synchronized (this) {
            System.out.println("synchronized 代码块");
        }
    }

    public synchronized void method01(){

        System.out.println("synchronized 方法");
    }

    public static void main(String[] args) {

    }
}

