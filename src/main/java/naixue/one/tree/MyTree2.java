package naixue.one.tree;

import java.util.Stack;

/**
 *  非递归遍历
 * @author dzd
 */
public class MyTree2 {

    public void prePrintTree(TreeNode root) {
        if(root==null) {
            System.out.println("空树");
            return;
        }
        TreeNode tmp=root;
        Stack<TreeNode> s=new Stack<>();
        s.push(tmp);  //根节点入栈
        while(!s.empty()) {
            //1.访问根节点
            TreeNode p=s.pop();
            System.out.print(p.val+" ");
            if(p.right!=null) {
                s.push(p.right);
            }
            if(p.left!=null) {
                s.push(p.left);
            }
        }
        System.out.println();
    }


    public void midPrintTree(TreeNode root) {
        if(root==null) {
            System.out.println("空树");
            return;
        }
        TreeNode tmp=root;
        Stack<TreeNode> s=new Stack<>();
        while(tmp!=null || !s.empty()) {
            //1.将根节点入栈
            while(tmp!=null) {
                s.push(tmp);
                tmp=tmp.left;
            }
            tmp=s.pop();
            System.out.print(tmp.val+" ");
            if(tmp.right!=null) {
                tmp=tmp.right;
            }
            //否则，将tmp置为null，表示下次要访问的是栈顶元素
            else {
                tmp=null;
            }
        }
        System.out.println();
    }

    public void afterPrintTree(TreeNode root) {
        if(root==null) {
            System.out.println("空树");
            return;
        }
        //当前节点
        TreeNode tmp=root;
        //上一次访问的节点
        TreeNode prev=null;
        Stack<TreeNode> s=new Stack<>();
        while(tmp!=null || !s.empty()) {
            //1.将根节点及其左孩子入栈
            while(tmp!=null) {
                s.push(tmp);
                tmp=tmp.left;
            }

            if(!s.empty()) {
                //2.获取栈顶元素值
                tmp=s.peek();
                //3.没有右孩子，或者右孩子已经被访问过
                if(tmp.right==null || tmp.right==prev) {
                    //则可以访问栈顶元素
                    tmp=s.pop();
                    System.out.print(tmp.val+" ");
                    //标记上一次访问的节点
                    prev=tmp;
                    tmp=null;
                }
                //4.存在没有被访问的右孩子
                else {
                    tmp=tmp.right;
                }
            }
        }
        System.out.println();
    }
}
