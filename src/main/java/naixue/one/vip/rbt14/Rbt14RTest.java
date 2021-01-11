package naixue.one.vip.rbt14;

import naixue.one.mytree.RBTree;

/**
 * 测试红黑树
 *
 *           89
 *         /   \
 *        8     92
 *       / \
 *      3   81
 *     /\
 *    2  4
 *
 * @author dzd
 */
public class Rbt14RTest {

    public static void main(String[] args) {
        Rbt14<Integer> tree = new Rbt14<>();
        tree.addEle(8);
        tree.addEle(89);
        tree.addEle(3);
        tree.addEle(81);
        tree.addEle(92);
        tree.addEle(2);
        tree.addEle(4);
        tree.addEle(999);
        tree.addEle(888);
        tree.addEle(0);
        tree.addEle(56);

        tree.preOrder();

        System.out.println();
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
