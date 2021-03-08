package naixue.six.mesi;

import org.openjdk.jol.info.ClassLayout;

public class ObjectSize {

    public static void main(String[] args) {
        FalseSharing.VolatileLong volatileLong=new FalseSharing.VolatileLong();
        System.out.println(ClassLayout.parseInstance(volatileLong).toPrintable());

        FalseSharing.VolatileLong1 volatileLong1=new FalseSharing.VolatileLong1();
        System.out.println(ClassLayout.parseInstance(volatileLong1).toPrintable());

        FalseSharing.VolatileLong3 volatileLong3=new FalseSharing.VolatileLong3();
        System.out.println(ClassLayout.parseInstance(volatileLong3).toPrintable());
    }
}
