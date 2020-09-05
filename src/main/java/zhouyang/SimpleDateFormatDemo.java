package zhouyang;


import java.text.SimpleDateFormat;

public class SimpleDateFormatDemo {
   static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal=new ThreadLocal<SimpleDateFormat>(){
       @Override
       protected SimpleDateFormat initialValue() {
           return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       }
   };
  /** //运行此段代码发现会报NumberFormatException 因为此为线程不安全的
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    System.out.println(simpleDateFormat.parse("2017-12-11 12:12:12"));;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }*/

    /**
     * 使用threadlocal即可 保证每个线程使用一个就不会出现问题了，然而需要注意用完回收防止内存泄漏
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    System.out.println(simpleDateFormatThreadLocal.get().parse("2017-12-11 12:12:12"));;
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    simpleDateFormatThreadLocal.remove();
                }
            }).start();
        }
    }
}
