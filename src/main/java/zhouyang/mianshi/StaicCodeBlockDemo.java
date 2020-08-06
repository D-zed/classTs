package zhouyang.mianshi;

/**
 * 从父到子 静态先行
 */
public class StaicCodeBlockDemo {

    public static void main(String[] args) {
        System.out.println("-----------分割线1--------------");
        //第一次的时候加载会将父类也加载了
        Son son = new Son();
        System.out.println("-----------分割线2--------------");
        //每一次new 会先调用父类的空构造方法
        Son son1 = new Son();
        System.out.println("-----------分割线3--------------");
        Father father = new Father();
    }
}
class Son extends Father{

    public Son() {
        System.out.println("我是儿子的构造方法");
    }

    {
        System.out.println("儿子的代码块");
    }
    static {
        System.out.println("儿子的静态代码块");
    }
}

class Father{

    String aa;

    public Father(String aa) {
        System.out.println("我是爸爸的构造方法");
        this.aa=aa;
    }

    public Father() {
        System.out.println("我是爸爸的构造方法空的");

    }

    {
        System.out.println("爸爸的代码块");
    }
    static {
        System.out.println("爸爸的静态代码块");
    }
}