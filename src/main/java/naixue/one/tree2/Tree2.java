package naixue.one.tree2;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 树
 * @author dzd
 */
public class Tree2 {


    /**
     * 前序
     * @param root 节点
     */
    public void preOrder(TreeNode2 root){
        if (root!=null){
            System.out.print(root.value);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    /**
     * 中序
     * @param root 节点
     */
    public void midOrder(TreeNode2 root){
        if (root!=null){
            preOrder(root.left);
            System.out.print(root.value);
            preOrder(root.right);
        }
    }

    /**
     * 后序
     * @param root 节点
     */
    public void postOrder(TreeNode2 root){
        if (root!=null){
            preOrder(root.left);
            preOrder(root.right);
            System.out.print(root.value);
        }
    }


    /**
     * 前序非递归法
     * @param root 节点
     */
    public void preOrder2(TreeNode2 root){

        if (root==null){
            System.out.println("空树");
            return;
        }

        TreeNode2 tmp=root;

        LinkedList<TreeNode2> list=new LinkedList<>();
        list.addFirst(tmp);

        while (!list.isEmpty()){
            //先序遍历 先输出根
            TreeNode2 parent = list.pollFirst();
            System.out.print(parent.value);
            //由于栈的特性后进先出，所以先判断右边后判断左边这样能保证左边永远先出栈，所以左子树也肯定优于右子树遍历完成
            if (parent.right!=null){
                list.addFirst(parent.right);
            }
            if (parent.left!=null){
                list.addFirst(parent.left);
            }
        }
        System.out.println();
    }


    /**
     * 中序非递归法
     * @param root 节点
     */
    public void midOrder2(TreeNode2 root){

        if (root==null){
            System.out.println("空树");
            return;
        }

        TreeNode2 rootTmp=root;
        TreeNode2 right = rootTmp.right;
        LinkedList<TreeNode2> list=new LinkedList<>();
        list.addFirst(rootTmp);

        while (!list.isEmpty()){
            //先序遍历 先输出根
            TreeNode2 parent  = list.pollFirst();
            if (!parent.equals(rootTmp)){
                if (parent.equals(right)){
                    System.out.println(rootTmp.value);
                }
                System.out.print(parent.value);
            }

            //由于栈的特性后进先出，所以先判断右边后判断左边这样能保证左边永远先出栈，所以左子树也肯定优于右子树遍历完成
            if (parent.right!=null){
                list.addFirst(parent.right);
            }
            if (parent.left!=null){
                list.addFirst(parent.left);
            }
        }
        System.out.println();
    }

    /**
     * 后序非递归法
     * @param root 节点
     */
    public void postOrder2(TreeNode2 root){

        if (root==null){
            System.out.println("空树");
            return;
        }

        TreeNode2 tmp=root;

        LinkedList<TreeNode2> list=new LinkedList<>();
        list.addFirst(tmp);

        while (!list.isEmpty()){
            //先序遍历 先输出根
            TreeNode2 parent = list.pollFirst();
            if (!parent.equals(tmp)){
                System.out.print(parent.value);
            }
            //由于栈的特性后进先出，所以先判断右边后判断左边这样能保证左边永远先出栈，所以左子树也肯定优于右子树遍历完成
            if (parent.right!=null){
                list.addFirst(parent.right);
            }
            if (parent.left!=null){
                list.addFirst(parent.left);
            }
        }
        System.out.print(tmp.value);
        System.out.println();
    }


}
