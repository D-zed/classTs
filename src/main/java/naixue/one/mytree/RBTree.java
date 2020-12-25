package naixue.one.mytree;

import java.util.LinkedList;

/**
 * 红黑树实现添加，前序遍历
 *
 * @author dzd
 */
public class RBTree<E extends Comparable<E>> {

    Boolean RED = Boolean.TRUE;
    Boolean BLACK = Boolean.FALSE;

    Node root;

    int size;

    public int getSize() {
        return size;
    }

    ;

    public void addEle(E e) {
        root = addEle(root, e);
        root.color = BLACK;
    }

    private Node addEle(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(null, null, e);
        }
        if (e.compareTo(node.val) < 0) {
            node.left = addEle(node.left, e);
        } else {
            node.right = addEle(node.right, e);
        }
        //如果节点添加到了右边，那么当左旋，因为我们这个为左倾红黑树
        if (!isRed(node.left) && isRed(node.right)) {
            //左旋
            node = leftRote(node);
        }

        //如果变成了一个四节点了那么需要右旋，后续还要判断分裂（也就是颜色转换）
        if (isRed(node.left) && isRed(node.left.left)) {
            //左旋
            node = rightRote(node);
        }

        if (isRed(node.left) && isRed(node.right)) {
            flipColor(node);
        }


        return node;

    }

    /**
     * 右旋
     *
     * @param node
     * @return
     */
    private Node rightRote(Node node) {

        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;

        return x;
    }

    /**
     * node               x
     * a     x        node  c
     * b  c      a  b
     *
     * @param node
     * @return
     */
    private Node leftRote(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private void flipColor(Node node) {
        node.left.color = BLACK;
        node.right.color = BLACK;
        node.color = RED;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    private class Node {
        Node left;
        Node right;
        Boolean color;
        E val;

        public Node(Node left, Node right, E val) {
            this.left = left;
            this.right = right;
            this.color = RED;
            this.val = val;
        }
    }


    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node root) {
        if (root==null){
            System.out.println("空树");
            return;
        }
        LinkedList<Node> linkedList=new LinkedList<>();
        linkedList.addFirst(root);
        while (!linkedList.isEmpty()){
            Node node = linkedList.pollFirst();
            System.out.print(node.val+" ");
            if (node.right!=null){
                linkedList.addFirst(node.right);
            }
            if (node.left!=null){
                linkedList.addFirst(node.left);
            }
        }
    }

}
