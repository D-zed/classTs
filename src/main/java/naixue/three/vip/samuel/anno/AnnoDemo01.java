package naixue.three.vip.samuel.anno;

import java.util.Date;

public class AnnoDemo01 {

    @Deprecated
    public void shownx01(){
        System.out.println("nx nx...");
    }

    @MyAnno(showStr = "hello",showStrArr = {"hello","nx"},showAnno = @MyAnno2(),showEnum = ColorEnum.BlACk)
    public void shownx02(){
        System.out.println("nx nx nx...");
    }

    @MyAnno2("samuel")
    public void shownx03(){

    }

}

@SuppressWarnings("all")
class Demo{
    public static void main(String[] args) {
        AnnoDemo01 annoDemo01 = new AnnoDemo01();
        annoDemo01.shownx01();
        Date date = new Date();
        date.getYear();
    }
}

@FunctionalInterface
interface myInterface{
    void test01();
}
