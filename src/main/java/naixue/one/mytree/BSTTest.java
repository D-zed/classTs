package naixue.one.mytree;

/**
 *      8
 *    /  \
 *   3   89
 *  /\   / \
 * 2 4 81   92
 *
 * @author dzd
 */
public class BSTTest {

    public static void main(String[] args) {
        BSTree<Integer> tree = new BSTree<>();
        tree.addEle(8);
        tree.addEle(89);
        tree.addEle(3);
        tree.addEle(81);
        tree.addEle(92);
        tree.addEle(2);
        tree.addEle(4);
        System.out.println("树的size ："+tree.getSize());
        tree.preOrder();
        System.out.println();
        tree.preOrderDg();
    }
}
