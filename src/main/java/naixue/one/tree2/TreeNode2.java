package naixue.one.tree2;

/**
 * 定义一个树的结点
 * @author dzd
 */
public class TreeNode2 {
    protected Integer value;

    protected TreeNode2 left;

    protected TreeNode2 right;

    public TreeNode2(Integer value, TreeNode2 left, TreeNode2 right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public TreeNode2(Integer value) {
        this.value = value;
    }


}
