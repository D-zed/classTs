package naixue.one.vip.rbt;

public class RBTMain {

    public static void main(String[] args) {
        RBT<Integer> rbt = new RBT<>();
        rbt.addEle(8);
        rbt.addEle(89);
        rbt.addEle(3);
        rbt.addEle(81);
        rbt.addEle(92);
        rbt.addEle(2);
        rbt.addEle(4);
        rbt.levelOrder();
    }
}
