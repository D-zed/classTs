package naixue.four.naixue.vip.p6.jvm.oom;

import java.util.ArrayList;
import java.util.List;

//-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\\nxjy -Xmx2m -Xms2m
public class HeapTest {


    public static void main(String[] args) {
//        newObjectOOM();
        constantOOM();
//        while (true){}
    }

    private static void newObjectOOM() {
        List<Integer> list = new ArrayList<>();
        while (true) {
            list.add(new Integer(1));//Integer -127---128
        }
    }

    /**
     * 线程过多
     */
    private static void newThreadOOM() {
        try {
            while (true) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                        }
                    }
                });
                System.out.println(thread.getName());
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 常量池溢出
     */
    // -Xmx10m -Xms10m
    private static void constantOOM() {
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}

