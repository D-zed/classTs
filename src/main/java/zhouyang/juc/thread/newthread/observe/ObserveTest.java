package zhouyang.juc.thread.newthread.observe;

public class ObserveTest {

    public static void main(String[] args) {

        ObserveTask<String> stringObserveTask = new ObserveTask<String>() {
            @Override
            public String call() {
                System.out.println("真正执行的线程逻辑");
                return "hehe";
            }
        };

        ObserveEvent<String> observeEvent=new ObserveEvent<String>() {
            @Override
            public void start() {
                System.out.println("start");
            }

            @Override
            public void running() {
                System.out.println("running");
            }

            @Override
            public void result(String result) {
                System.out.println("result"+result);
            }

            @Override
            public void exception(String result, Exception e) {
                System.out.println("exception"+e);
            }
        };

        ObserveThread<String> stringObserveThread = new ObserveThread<String>(observeEvent,stringObserveTask);

        stringObserveThread.start();

    }
}
