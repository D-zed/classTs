package zhouyang.juc;

/**
 * 指令重排会造成的问题
 * @author dzd
 */
public class ChongPaiDemo {
    private static int num=0;
    private static boolean ready=false;

    /**
     * 由于这四步没有必然联系所以输出结果是不确定的
     */
    public static class ReadThread extends Thread{
        @Override
        public void run() {

            while (!Thread.currentThread().isInterrupted()){
                if (ready){ //1
                    System.out.println(num+num); //2
                }
            }
        }
    }
    public static class WriteThread extends Thread{
        @Override
        public void run() {
            num=2;   //3
            ready=true;//4
            System.out.println("writeThread set over..");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadThread readThread = new ReadThread();
        readThread.start();

        WriteThread writeThread = new WriteThread();
        writeThread.start();
        Thread.sleep(1);
        readThread.interrupt();

        System.out.println("main exit");

    }
}
