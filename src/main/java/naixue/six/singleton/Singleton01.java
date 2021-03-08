package naixue.six.singleton;


public class Singleton01 {

    private static Singleton01 INSTANCE;

    public static Singleton01 getInstance(){
        if(INSTANCE == null){

            INSTANCE = new Singleton01();
        }
        return INSTANCE;
    }
    private Singleton01(){}

}
