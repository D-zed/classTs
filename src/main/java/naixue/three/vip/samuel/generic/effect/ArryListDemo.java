
package naixue.three.vip.samuel.generic.effect;

import java.util.ArrayList;

/**
 *
 */
public class ArryListDemo {

    public void hasGen(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(125);
        list.add(126);
        System.out.println("------------");
        System.out.println(list.get(0).compareTo(list.get(1)));

    }

    public void noGen(){
        ArrayList list = new ArrayList<>();
        list.add(125);
        list.add(126);
        System.out.println("------------");
        System.out.println(((Integer)list.get(0)).compareTo((Integer)list.get(1)));

    }
}
