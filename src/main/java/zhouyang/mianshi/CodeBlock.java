package zhouyang.mianshi;

/**
 * 代码块的内容
 * 在方法或语句中出现的 {}称之为普通代码块
 * 先出现先加载
 *
 * 然而静态块的优先级更高
 * @author dengzidi
 */
public class CodeBlock {

    {
        System.out.println("第一块");
    }
    public CodeBlock(){
        System.out.println("构造");
    }
    {
        System.out.println("第二块");
    }


}
class Code{

    public Code(){
        System.out.println("Code的构造方法1");
    }
    {
        System.out.println("Code的构造块1");
    }
    static {
        System.out.println("Code的静态块1");
    }

    public static void main(String[] args) {
        //由于main在Code中运行的所以类首先要加载 所以必然先执行静态块
        //在上面就不是这样了
        System.out.println("=======静态块有我快？===========");
        new Code();
        //构造代码块的优先级大于构造方法
        CodeBlock codeBlock = new CodeBlock();
        System.out.println("==================");
        CodeBlock codeBlock1 = new CodeBlock();
    }
}
