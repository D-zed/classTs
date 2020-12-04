package zhouyang.fanxing.fanxingmethod;

import zhouyang.fanxing.result.Result;

/**
 * 泛型方法
 * 泛型方法，是在调用方法的时候指明泛型的具体类型
 * @author dzd
 */
public class ParentM {

    public <E> void getA(E... data) {
        for (E item : data) {
            System.out.println(item);
        }
    }

    /**
     * 静态方法如果想用泛型必须得成为泛型静态方法
     * @param a
     * @param <T>
     */
    public  static <T> void getB(T... a){
        for (T item : a) {
            System.out.println(item);
        }
    }
}