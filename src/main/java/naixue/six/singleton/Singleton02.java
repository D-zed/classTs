
package naixue.six.singleton;


import java.util.concurrent.TimeUnit;

public class Singleton02 {

    private static volatile Singleton02 INSTANCE;

    public static  Singleton02 getInstance(){
        if(INSTANCE == null){
            synchronized (Singleton02.class){ // INSTANCE是否需要可见呢？
                if (INSTANCE == null){
                    try {
                        TimeUnit.SECONDS.sleep(20);
                        INSTANCE = new Singleton02();
                        /**
                         * new Singletono2 分配空间
                         * init
                         * 指向
                         */
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


        }
        return INSTANCE;
    }
    private Singleton02(){}

}
