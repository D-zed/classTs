package designmode.templatemode;

/**
 * 模板模式：父类编写算法结构代码 ，子类实现逻辑细节
 * 程序结构由父类决定 且父方法为final 不能重写
 *  线程 的start方法就是模板模式
 * @author dzd
 */
public class TemplateMode {

    public final void print(String message){
        System.out.println("##############");
        wrapPrint(message);
        System.out.println("##############");
    }

    protected void wrapPrint(String message) {

    }

    public static void main(String[] args) {

        TemplateMode templateMode = new TemplateMode(){
            @Override
            protected void wrapPrint(String message) {
                System.out.println(message);
            }
        };

        templateMode.print("模板模式细节");

        TemplateMode templateMode1 = new TemplateMode(){
            @Override
            protected void wrapPrint(String message) {
                System.out.println(message);
            }
        };

        templateMode1.print("模板模式细节2");

    }
}
