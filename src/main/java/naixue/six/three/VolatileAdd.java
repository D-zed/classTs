
package naixue.six.three;

/**
 * HSDIS
 * JIT 是查看汇编指令的插件
 * hsdis-amd64.dll
 *
 */
public class VolatileAdd {

    static volatile int a = 0;
    static volatile int b = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            add();
        }
    }

    public static void add() {
        for (int i = 0; i < 100; i++) {
            a++;
            b += 2;
        }
    }
}
