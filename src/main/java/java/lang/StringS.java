package java.lang;

/**
 * 用于理解类加载器的双亲委派
 * @author dzd
 */
public class StringS {

    //为了保证我们的代码（当我们的类包都和系统lib下的相同时候）不污染自带的代码，使用双亲委派机制
    //
    public static void main(String[] args) {
        System.out.println("hhhhhhh");
    }
}