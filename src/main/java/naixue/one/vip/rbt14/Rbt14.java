package naixue.one.vip.rbt14;

import java.util.LinkedList;

/**
 * 20210104复习红黑树
 *
 * @author dzd
 */
public class Rbt14<T extends Comparable<T>> {

    Node<T> root;
    public static Boolean RED = Boolean.TRUE;
    public static Boolean BLACK = Boolean.FALSE;

    /**
     * 添加元素
     */
    public void addEle(T val) {
        root = addEle(val, root);
        root.color = BLACK;
    }

    private Node<T> addEle(T val, Node<T> node) {
        if (node == null) {
            //添加新节点默认是红色
            return new Node<T>(val, RED);
        }
        //如果插入的是树的左边
        if (val.compareTo(node.val) < 0) {
            node.left = addEle(val, node.left);
        }
        //如果插入的是树的右边
        else {
            node.right = addEle(val, node.right);
        }

        //插入节点之后开始考虑颜色问题 ,本红黑树是个左倾红黑树，所以当节点插入到了右，则需要左旋
        if (!isRed(node.left) && isRed(node.right)) {
            node = leftRote(node);
        }

        //插入节点之后开始考虑颜色问题 ,本红黑树是个左倾红黑树，如果左边的左边依然是红色的则要右旋
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRote(node);
        }
        //如果左边和右边都是红色，则需要颜色反转，
        if (isRed(node.left) && isRed(node.right)) {
            flipColor(node);
        }

        return node;
    }

    private void flipColor(Node<T> node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    private Node<T> rightRote(Node<T> node) {

        Node<T> x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    /**
     * 左旋
     *
     * @param node node
     * @return Node
     */
    private Node<T> leftRote(Node<T> node) {
        Node<T> x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private boolean isRed(Node<T> node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    /**
     * 定义树节点
     */
    private static class Node<T> {
        T val;
        Boolean color;
        Node<T> left;
        Node<T> right;

        public Node(T val, Boolean color) {
            this.val = val;
            this.color = color;
        }

        public Node() {

        }

        public T getVal() {
            return val;
        }

        public void setVal(T val) {
            this.val = val;
        }

        public Boolean getColor() {
            return color;
        }

        public void setColor(Boolean color) {
            this.color = color;
        }
    }

    public void preOrder() {
        if (root == null) {
            System.out.println("此为一棵空树");
            return;
        }
        LinkedList<Node<T>> linkedList = new LinkedList<>();
        linkedList.addFirst(root);
        while (!linkedList.isEmpty()) {
            Node<T> tNode = linkedList.pollFirst();
            System.out.println("val:" + tNode.val + " color:" + (tNode.color ? "红" : "黑"));
            if (tNode.right != null) {
                linkedList.addFirst(tNode.right);
            }
            if (tNode.left != null) {
                linkedList.addFirst(tNode.left);
            }
        }
    }

}
