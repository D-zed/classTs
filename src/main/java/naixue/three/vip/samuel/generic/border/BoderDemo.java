package naixue.three.vip.samuel.generic.border;

import java.util.ArrayList;

/**
 * 泛型通配符：边界的问题
 * 三种边界
 * 无界：？<?>
 * 上界： <? extends E>
 * 下界： <? super E>
 */
public class BoderDemo {

    /**
     * 无界通配符的使用
     */
    public void border01(ArrayList<?> arrayList){
        for (int i=0;i<arrayList.size();i++){
            System.out.println(arrayList.get(0));
        }
    }

    /**
     * 上界通配符的使用
     * <? extends Object> 无界通配符
     */
    public void border02(ArrayList<? extends Number> arrayList){
        for (int i=0;i<arrayList.size();i++){
            System.out.println(arrayList.get(0));
        }
    }

    /**
     * 下界通配符的使用
     */
    public void border03(ArrayList<? super Number> arrayList){
        for (int i=0;i<arrayList.size();i++){
            System.out.println(arrayList.get(0));
        }
    }

    public static void main(String[] args) {
        BoderDemo boderDemo = new BoderDemo();
        ArrayList<String> strList = new ArrayList<>();
        ArrayList<Object> objList = new ArrayList<>();
        ArrayList<Number> numberArrayList = new ArrayList<>();
        ArrayList<Double> doubleArrayList = new ArrayList<>();
        ArrayList<Integer> intArrayList = new ArrayList<>();

        //
        boderDemo.border01(strList);
        boderDemo.border01(numberArrayList);

        //上界

        boderDemo.border02(intArrayList);

        //下届
//        boderDemo.border03(doubleArrayList);
    }
}
