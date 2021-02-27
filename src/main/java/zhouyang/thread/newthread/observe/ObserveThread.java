package zhouyang.thread.newthread.observe;

public class ObserveThread<T> extends Thread {

    private final ObserveEvent<T> observeEvent;


    private final ObserveTask<T> observeTask;

    public ObserveThread(ObserveEvent<T> observeEvent,ObserveTask<T> observeTask){
        this.observeEvent=observeEvent;
        this.observeTask=observeTask;
    }


    @Override
    public void run() {
        try {
            eventHandle(EnumEvent.START);
            eventHandle(EnumEvent.RUNNING);
            T call = observeTask.call();
            eventHandle(EnumEvent.RESULT, call);
        }catch (Exception e){
            eventHandle(EnumEvent.ERROR,e);
        }
    }

    void eventHandle(EnumEvent enumEvent, T result,Exception e) {
        try {
            switch (enumEvent) {
                case START:
                    this.observeEvent.start();
                    break;
                case RUNNING:
                    this.observeEvent.running();
                    break;
                case RESULT:
                    this.observeEvent.result(result);
                    break;
                case ERROR:
                    this.observeEvent.exception(result,e);
                    break;
                default:
                    System.out.println("default ");
            }
        }catch (Exception ex){
            this.observeEvent.exception(result,e);
        }

    }


    void eventHandle(EnumEvent enumEvent, T result) {
        eventHandle(enumEvent,result,null);
    }

    void eventHandle(EnumEvent enumEvent) {
        eventHandle(enumEvent,null,null);
    }

    void eventHandle(EnumEvent enumEvent,Exception e) {
        eventHandle(enumEvent,null,e);
    }
}
