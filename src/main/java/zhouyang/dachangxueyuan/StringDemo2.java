package zhouyang.dachangxueyuan;

public class StringDemo2 {
    public static void main(String[] args) {

        String haha="haha";
        String che="che";

        String hahache =haha+che;

        String hahachee="hahache";

        System.out.println(hahache.intern()==hahachee);
    }
}
