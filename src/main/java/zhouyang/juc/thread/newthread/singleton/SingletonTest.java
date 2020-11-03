package zhouyang.juc.thread.newthread.singleton;

public class SingletonTest {
    public static void main(String[] args) {
      /*  SingletonHolder singletonHolder = new SingletonHolder();

        SingletonHolder instance = SingletonHolder.getInstance();
        SingletonHolder hh = SingletonHolder.getInstance();
*/

        SingletonEnumHolder.getInstance();
        SingletonEnumHolder.getInstance();
    }
}
