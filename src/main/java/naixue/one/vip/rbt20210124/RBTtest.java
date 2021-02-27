package naixue.one.vip.rbt20210124;


public class RBTtest {

    public static void main(String[] args) {
        RBTree<Integer> rbTree=new RBTree<>();

        rbTree.addEle(8);
        rbTree.addEle(89);
        rbTree.addEle(3);
        rbTree.addEle(81);
        rbTree.addEle(92);
        rbTree.addEle(2);
        rbTree.addEle(4);
        rbTree.addEle(999);
        rbTree.addEle(888);
        rbTree.addEle(0);
        rbTree.addEle(56);
        rbTree.preOrder();
    }
}
