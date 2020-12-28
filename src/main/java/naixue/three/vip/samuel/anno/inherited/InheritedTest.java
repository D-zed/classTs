package naixue.three.vip.samuel.anno.inherited;

public class InheritedTest {

    static class InheritedParent{}

    static class InheritedChild extends InheritedParent{}

    @InheritedAnnoTest
    static class Parent{}

    static class Child extends Parent{}

    public static void main(String[] args) {
        System.out.println(InheritedChild.class.getAnnotation(InheritedAnnoTest.class));;
        System.out.println(Child.class.getAnnotation(InheritedAnnoTest.class));;
    }
}
