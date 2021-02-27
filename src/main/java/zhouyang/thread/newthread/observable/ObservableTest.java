package zhouyang.thread.newthread.observable;

/**
 * @author dzd
 */
public class ObservableTest {

    public static void main(String[] args) {

        TaskLifecycle<String> lifecycle = new TaskLifecycle<String>() {
            @Override
            public void onStart(Thread thread) {
                System.out.println("onstart");
            }

            @Override
            public void onRunning(Thread thread) {
                System.out.println("onRunning");
            }

            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println("onFinish result "+result);
            }

            @Override
            public void onError(Thread thread, Exception e) {
                System.out.println("onFinish"+e);
            }
        };
        Task<String> task=new Task<String>() {
            @Override
            public String call() {
                System.out.println("call");
                return "call";
            }
        };

        ObservableThread observableThread = new ObservableThread<>(lifecycle,task);

        observableThread.start();
    }
}
