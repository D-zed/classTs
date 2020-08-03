package zhouyang.jvm;

/**
 * java.lang.StackOverflowError
 * @author dengzidi
 */
public class StackOverflowErrorDemo {

    public static void main(String[] args) {

        stackOverFlowError();
    }

    /**
     * 循环调用导致栈溢出
     */
    private static void  stackOverFlowError(){
        stackOverFlowError();
    }
}
