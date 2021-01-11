package zhouyang.jvm;

/**
 * 这个主要是考验 intern的首次遇见原则，也就是说 intern返回的是第一个加入到线程池的字符串
 * https://blog.csdn.net/seu_calvin/article/details/52291082#
 * @author ASUS
 */
public class StringInternDemo {

    /**
     * 所以说intern的作用就是强行使用池子里的字符串 如果有就直接用 如果没有将其加入池子再用
     * @param args
     */
    public static void main(String[] args) {
        //str2不加是true 加上是false 首先向池子里拿到
       String str2 = "DSEUCalvin";//
        String str22 = "DSEUCalvin";
       String str3 = "D";//

        // true   字符串在常量池中唯一
        System.out.println(str2==str22);
        //这个拼接产生的新对象在堆中
        String str1 = str3+new String("SEU")+ new String("Calvin");
        //intern默认先去池子里寻找 所以此时二者指向的是同一个了   true
        System.out.println(str1.intern()==str2);
        //然后str1 默认实在堆中的所以池中的和堆中的地址肯定不同的  false
        System.out.println(str1.intern() == str1);
        //false
        System.out.println(str1 == str2);
        //false
        System.out.println(str1 == "SEUCalvin");
    }
}
