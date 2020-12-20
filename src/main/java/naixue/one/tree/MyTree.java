package naixue.one.tree;

/**
 * 树的递归遍历
 * @author dzd
 */
public class MyTree {

    public  void prePrintTree(TreeNode t){
        if(t!=null){
            System.out.print(t.val+" ");
            prePrintTree(t.left);
            prePrintTree(t.right);
        }
    }

    /**
     * 中序遍历
     * @param t
     */
    public  void midPrintTree(TreeNode t){
        if(t!=null){
            midPrintTree(t.left);
            System.out.print(t.val+" ");
            midPrintTree(t.right);
        }
    }

    /**
     * @param t
     */
    public  void afterPrintTree(TreeNode t){
        if(t!=null){
            afterPrintTree(t.left);
            afterPrintTree(t.right);
            System.out.print(t.val+" ");
        }
    }
}
