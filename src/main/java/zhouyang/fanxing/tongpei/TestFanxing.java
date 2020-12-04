package zhouyang.fanxing.tongpei;

import java.util.ArrayList;
import java.util.List;

/**
 * 类/接口<? extends 实参类型>
 *     要求该泛型的类型，只能是实参类型，或实参类型的 子类 类型
 * 类/接口<? super 实参类型>
 *     要求该泛型的类型，只能是实参类型，或实参类型的 父类 类型
 * @author dzd
 */
public class TestFanxing {

    private static void printDataExtent(List<? extends ParentTongChild> list) {
        for (ParentTongChild item : list) {
            System.out.println(item);
        }
    }

    private static void printDataSuper(List<? super ParentTongChild> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }



    public static void main(String[] args) {
        //此泛型表示通配所有超类是 ParentTong的类型
        List<? super ParentTongChild> list=new ArrayList<>();
        //统配所有继承自ParentTong的类
        List<? extends ParentTongChild> list2=new ArrayList<>();

        ParentTongChild parentTongChild = new ParentTongChild();
        parentTongChild.setName("child");
        ParentTongSon parentTongSon=new ParentTongSon();
        parentTongSon.setAge(24);
        ParentTong parentTong=new ParentTong();
        parentTong.setId(1);
        list.add(parentTongChild);
        list.add(parentTongSon);
        System.out.println(list.toString());

        List<ParentTong> parentTongList=new ArrayList<>();
        List<ParentTongChild> parentTongChildList=new ArrayList<>();
        List<ParentTongSon> parentTongSonList=new ArrayList<>();

        // 所以这样实参内容只能是指定的子类，因为这个是上边界
        // printDataExtent(parentTongList);
        printDataExtent(parentTongChildList);
        printDataExtent(parentTongSonList);

        printDataSuper(parentTongList);
        printDataSuper(parentTongChildList);
        //printDataSuper(parentTongSonList);
    }

}