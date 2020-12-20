package naixue.one.tree;

/**
 * 初始化树如下
 *      9
 *     / \
 *    7   8
 *   /\   /
 *  5  1  6
 *  \     /\
 *   2   3  4
 */
public class Ts {
    public static void main(String[] args) {
        //初始化一棵树
        TreeNode e = new TreeNode(1);
        TreeNode g = new TreeNode(2);
        TreeNode h = new TreeNode(3);
        TreeNode i = new TreeNode(4);
        TreeNode d = new TreeNode(5,null,g);
        TreeNode f = new TreeNode(6,h,i);
        TreeNode b = new TreeNode(7,d,e);
        TreeNode c = new TreeNode(8,f,null);
        TreeNode root = new TreeNode(9,b,c);

        MyTree myTree = new MyTree();
        System.out.println("先序递归");
        myTree.prePrintTree(root);
        System.out.println();
        System.out.println("中序递归");
        myTree.midPrintTree(root);
        System.out.println();
        System.out.println("后续递归");
        myTree.afterPrintTree(root);

        System.out.println();
        MyTree2 myTree2 = new MyTree2();
        System.out.println("先序非递归");
        myTree2.prePrintTree(root);
        System.out.println("中序非递归");
        myTree2.midPrintTree(root);
        System.out.println("后续非递归");
        myTree2.afterPrintTree(root);
    }
}
