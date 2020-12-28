package naixue.three.vip.samuel.generic.effect;

import java.io.File;

/**
 * 泛型的作用
 * 1、泛型可以保证类型安全
 * 2、避免强制转换的硬编码
 * 3、调到代码的重用性
 *
 * 模拟ArrayList的底层实现
 */
public class ArrrayListNoGen {

    private Object[] elements = {};
    private int size = 0;

    public Object get(int i){
        if(size>i){
            return elements[i];
        }
        throw new IndexOutOfBoundsException();
    }

    public void add(Object o){
        size ++;
        Object[] array = new Object[size];
        for(int i=0;i<elements.length;i++){
            array[i] = elements[i];
        }
        array[size-1]=o;
        elements = array;
    }

    public static void main(String[] args) {
        ArrrayListNoGen list = new ArrrayListNoGen();
        list.add(1);
        list.add("samuel");
        list.add(new File("/"));

        String str = (String) list.get(2);
    }
}
