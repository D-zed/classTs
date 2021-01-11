package naixue.four.naixue.vip.p6.jvm.bytecode;


public class StringTest {

    public static void m1(){
        String str = "";
        for (int i=0;i<10;i++){
            str = str+"nx,";
        }
        System.out.println(str);

    }

    public static void m2(){
        StringBuffer str = new StringBuffer();
        for (int i=0;i<10;i++){
            str.append("nx,");
        }
        System.out.println(str);

    }

    public static String m3(){
        String str = "hello";
        try {
           return str;
        }finally {
          str = "nx";
        }
    }

    public static void main(String[] args) {

    }
}

