package naixue.four.naixue.vip.p6.jvm.oom;

public class StackTest {
    public static void main(String[] args) {
        a();
    }

    public static String a() {
        return a();
    }
}
