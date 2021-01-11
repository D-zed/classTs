package naixue.four.naixue.vip.p6.jvm.cloader;

public class Main {

    public static void main(String[] args) throws Exception {

        //类加载器加载的目录
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
        //自定义加载器
        SelfClassLoader classLooader = new SelfClassLoader("d:\\nxjy\\", "samuel");
        Class<?> dogClazz = classLooader.loadClass("com.naixue.Dog");
        Object dogObj = dogClazz.newInstance();
        System.out.println(dogObj.getClass().getClassLoader());
        System.out.println(dogObj.getClass().getClassLoader().getParent());
        System.out.println(dogObj.getClass().getClassLoader().getParent().getParent());
        System.out.println(dogObj.getClass().getClassLoader().getParent().getParent().getParent());




    }
}
