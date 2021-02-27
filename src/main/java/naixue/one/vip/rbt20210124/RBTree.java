package naixue.one.vip.rbt20210124;

import java.util.LinkedList;

/**
 * @author dzd
 */
public class RBTree<T extends Comparable<T>> {
    private static final Boolean RED = Boolean.TRUE;
    private static final Boolean BLACK = Boolean.FALSE;
    Node<T> root;

    public void addEle(T val) {
        root = addEle(val, root);
        root.color = BLACK;
    }

    private Node<T> addEle(T val, Node<T> node) {
        if (node == null) {
            return new Node<>(val);
        }
        if (val.compareTo(node.val) < 0) {
            node.left = addEle(val, node.left);
        } else {
            node.right = addEle(val, node.right);
        }
        //1.左旋
        if (!isRed(node.left) && isRed(node.right)) {
            node = leftRote(node);
        }
        //2.右旋
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRote(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColor(node);
        }
        return node;
    }

    private Node<T> rightRote(Node<T> node) {
        Node<T> x = node.left;
        node.left = x.right;
        x.right = node;
        //旋转之后变换颜色
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private void flipColor(Node<T> node) {
        node.right.color = BLACK;
        node.left.color = BLACK;
        node.color = RED;
    }

    private Node<T> leftRote(Node<T> node) {
        Node<T> x = node.right;
        node.right = x.left;
        x.left = node;
        //变换颜色
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
     * 前序遍历
     */
    public void preOrder() {
        LinkedList<Node<T>> stack = new LinkedList<>();
        stack.addFirst(root);
        while (!stack.isEmpty()) {
            Node<T> tNode = stack.pollFirst();
            System.out.println("颜色：" + tNode.color + ", val:" + tNode.val);
            if (tNode.right != null) {
                stack.addFirst(tNode.right);
            }
            if (tNode.left != null) {
                stack.addFirst(tNode.left);
            }
        }

    }

    static class Node<T> {
        T val;
        Node<T> left;
        Node<T> right;
        Boolean color;

        public Node(T val) {
            this.val = val;
            this.color = RED;
        }
    }
}
