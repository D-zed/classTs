package naixue.six.three;

public class VoTest {
    public volatile int value;

    public static void main(String[] args) {
        new VoTest().add();
    }

    public void add() {
        value++; //1、获取到valeu,2、value+1,3、新值赋值给value

    }
}