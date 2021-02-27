package naixue.one.vip.rbt2021;

import java.util.LinkedList;

/**
 * 红黑树复习
 * 红黑树可以按照2-3树的方式理解
 * 新添加的节点都是红色的
 * 根节点都是黑色的
 * 左倾红黑树 所有的红色节点需要在树的左边
 *
 * @author dzd
 */
public class RBTree<T extends Comparable<T>> {

    /**
     * 红色
     */
    private final static Boolean RED = Boolean.TRUE;
    /**
     * 黑色
     */
    private final static Boolean BLACK = Boolean.FALSE;
    /**
     * 根节点
     */
    Node<T> root;

    /**
     * 添加节点
     */
    public void addEle(T t) {
        root = addEle(root, t);
        root.color = BLACK;
    }

    private Node<T> addEle(Node<T> currentNode, T t) {
        if (currentNode == null) {
            return new Node<T>(t);
        }
        //如果插入的是树的左边
        if (t.compareTo(currentNode.val) < 0) {
            currentNode.left = addEle(currentNode.left, t);
        }
        //如果插入的是树右边
        else {
            currentNode.right = addEle(currentNode.right, t);
        }
        //节点插入之后进行颜色调整
        //1.当插入了树的右边，则进行左旋
        if (!isRed(currentNode.left) && isRed(currentNode.right)) {
            currentNode = leftRote(currentNode);
        }
        //2.当插入了树的左边，则进行左旋
        if (isRed(currentNode.left) && isRed(currentNode.left.left)) {
            currentNode = rightRote(currentNode);
        }
        //3.当两边皆为红色，则颜色反转
        if (isRed(currentNode.left) && isRed(currentNode.right)) {
            flipColor(currentNode);
        }
        return currentNode;


    }

    private void flipColor(Node<T> currentNode) {

        currentNode.left.color = BLACK;
        currentNode.right.color = BLACK;
        currentNode.color = RED;

    }

    /**
     * 右旋
     *
     * @param currentNode
     * @return
     */
    private Node<T> rightRote(Node<T> currentNode) {
        Node x = currentNode.left;
        currentNode.left = x.right;
        x.right = currentNode;
        x.color = currentNode.color;
        currentNode.color = RED;
        return x;
    }

    /**
     * 左旋操作
     *
     * @param a
     * @return
     */
    private Node<T> leftRote(Node<T> a) {
        Node<T> c = a.right;
        a.right = c.left;
        c.left = a;
        c.color = a.color;
        a.color = RED;
        return c;
    }

    private Boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    /**
     * 前序遍历，也就是前根遍历，根放在最前边
     */
    public void preOrder(){
        if (root==null){
            System.out.println("此为空树");
            return;
        }
        //一个栈
        LinkedList<Node<T>> stack=new LinkedList<>();
        //将根节点入栈
        stack.addFirst(root);

        while (!stack.isEmpty()){
            Node<T> tNode = stack.pollFirst();
            System.out.println(tNode.val);
            if (tNode.right!=null){
                stack.addFirst(tNode.right);
            }
            if (tNode.left!=null){
                stack.addFirst(tNode.left);
            }
        }
    }


    /**
     * 定义树节点
     */
    static class Node<T> {

        T val;
        Node<T> left;
        Node<T> right;
        Boolean color;

        public Node(T val) {
            this.val = val;

            this.color = RED;
        }

        public T getVal() {
            return val;
        }

        public void setVal(T val) {
            this.val = val;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        public Boolean getColor() {
            return color;
        }

        public void setColor(Boolean color) {
            this.color = color;
        }
    }
}
