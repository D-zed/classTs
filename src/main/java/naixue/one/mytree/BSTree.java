package naixue.one.mytree;

import java.util.LinkedList;

/**
 * 二叉平衡树
 *
 * @author dzd
 */
public class BSTree<E extends Comparable<E>> {
    /**
     * 树的根节点
     */
    Node root;

    /**
     * 树的容量
     */
    int size;

    public int getSize(){
        return size;
    }


    /**
     * 添加树节点
     */
    public void addEle(E e) {
        root = addEle(root, e);
    }

    private Node addEle(Node currentNode, E e) {
        if (currentNode == null) {
            size++;
            return new Node(null, null, e);
        }
        //插到左边节点
        if (e.compareTo(currentNode.val) < 0) {
            currentNode.left = addEle(currentNode.left, e);
        }
        //插到右边节点
        else {
            currentNode.right = addEle(currentNode.right, e);
        }
        return currentNode;
    }


    private class Node {
        Node left;
        Node right;
        E val;

        public Node(Node left, Node right, E val) {
            this.left = left;
            this.right = right;
            this.val = val;
        }
    }


    /**
     * 前序遍历
     */
    public void preOrder() {
        if (root == null) {
            System.out.println("此树为空 ");
            return;
        }
        LinkedList<Node> linkedList = new LinkedList<>();
        linkedList.addFirst(root);
        //如果栈不是空
        while (!linkedList.isEmpty()) {
            Node node = linkedList.pollFirst();
            System.out.print(node.val);
            if (node.right != null) {
                linkedList.addFirst(node.right);
            }
            if (node.left != null) {
                linkedList.addFirst(node.left);
            }
        }

    }


    /**
     * 前序遍历递归遍历
     */
    public void preOrderDg() {
        if (root == null) {
            System.out.println("此树为空 ");
            return;
        }
        preOrderDg(root);

    }

    private void preOrderDg(Node root) {
        if (root==null){
            return;
        }
        System.out.print(root.val);
        preOrderDg(root.left);
        preOrderDg(root.right);
    }
}
