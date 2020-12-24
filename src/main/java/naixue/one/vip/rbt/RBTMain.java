package naixue.one.vip.rbt;

public class RBTMain {

    public static void main(String[] args) {
        RBT<Integer> rbt = new RBT<>();
        rbt.addEle(42);
        rbt.addEle(37);
        rbt.addEle(12);
        rbt.levelOrder();
    }
}
