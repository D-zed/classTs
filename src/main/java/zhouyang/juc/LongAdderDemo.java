package zhouyang.juc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderDemo {

    public static void main(String[] args) {

        AddClass a=new AddClass();
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    a.addInt();
                }
             // a.addLongAddr();
            });
            thread.setName(Integer.toString(i));
            thread.start();
        }

        while (Thread.activeCount()>2){
            Thread.yield();
        }

        System.out.println(a.a);
        System.out.println(a.longAdder.toString());
    }
}


class AddClass{
    LongAdder longAdder=new LongAdder();
    AtomicInteger aA=new AtomicInteger();
    int a=0;

    public void addInt(){
        a++;

    }

    public void addLongAddr(){
        longAdder.add(1);
    }
}
