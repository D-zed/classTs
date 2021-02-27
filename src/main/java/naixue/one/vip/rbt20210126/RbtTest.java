package naixue.one.vip.rbt20210126;

import naixue.one.vip.rbt.RBT;

public class RbtTest {

    public static void main(String[] args) {
        Rbt<Integer> rbt = new Rbt<>();
        rbt.addEle(8);
        rbt.addEle(89);
        rbt.addEle(3);
        rbt.addEle(81);
        rbt.addEle(92);
        rbt.addEle(2);
        rbt.addEle(4);
        rbt.preOrder();
    }
}
