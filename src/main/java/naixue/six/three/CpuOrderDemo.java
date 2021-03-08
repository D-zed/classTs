package naixue.six.three;

public class CpuOrderDemo {

    private  int x=0,y=0;
    private  int a=0,b=0;

    public static void main(String[] args) throws Exception{

        CpuOrderDemo cpuOrderDemo = new CpuOrderDemo();
        cpuOrderDemo.loopCall();


    }

    public void loopCall() throws InterruptedException {
        int i=0;
        while (true){
            i++;
            x=0;
            y=0;
            a=0;
            b=0;
            Thread t1 = new Thread(()->{
                a=1;
                x=b;
            });
            Thread t2 = new Thread(()->{
                b=2;
                y=a;
            });
            t1.start();
            t2.start();
            t1.join();;
            t2.join();
            if (x==0&&y==0){
                System.out.println("x="+x+"  y="+y+"  i="+i); //x=0,y=0??
                break;
            }

            /**
             * x=2,y=1
             * x=0，y=1  1
             * x=2，y=0  2
             * x=0，y=0  3
             */
        }
    }
}
