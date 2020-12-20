package naixue.one.tree2;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *       9
 *     /   \
 *    6     11
 *   / \    /\
 *  1   2  4  7
 *
 * @author dzd
 */
public class Ts {
    public static void main(String[] args) {
        //初始化树
        TreeNode2 a = new TreeNode2(1);
        TreeNode2 b = new TreeNode2(2);
        TreeNode2 c = new TreeNode2(4);
        TreeNode2 d = new TreeNode2(7);
        TreeNode2 e = new TreeNode2(6, a, b);
        TreeNode2 f = new TreeNode2(11, c, d);
        TreeNode2 root = new TreeNode2(9, e, f);

        Tree2 tree2 = new Tree2();
        tree2.preOrder(root);
        System.out.println();
        tree2.midOrder(root);
        System.out.println();
        tree2.postOrder(root);

        System.out.println();
       tree2.preOrder2(root);
        System.out.println();
       tree2.midOrder(root);
        System.out.println();
       tree2.postOrder(root);

    }
}
