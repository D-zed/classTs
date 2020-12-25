package naixue.one.vip.rbt;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 红黑树
 * @param <E>
 */
public class RBT<E extends Comparable<E>> {
    /**
     * 红黑定义
     */
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    /**
     * 树节点
     */
    public Node root;
    /**
     * 树的容量
     */
    private int size;

    public int size() {
        return size;
    }

    //添加节点,递归
    public void addEle(E e) {
        root = addEle(root, e);
        root.color = BLACK;
    }

    /**
     * 将元素E添加到以node为根的那个树上并且将新树的根返回
     *
     * @param currentNode 当前节点 第一次传入root节点
     * @param e
     * @return
     */
    private Node addEle(Node currentNode, E e) {
        //如果当前节点是空的那么说明此插入此位置
        if (currentNode == null) {
            size++;
            //返回当前节点
            return new Node(e);
        }

        if (e.compareTo(currentNode.e) < 0) {
            //如果添加数值小于当前节点的值，那么将递归与其左节点对比
            //生成了新节点之后将其赋值给 left
            currentNode.left = addEle(currentNode.left, e);
        } else {
            //如果添加数值大于当前节点的值，那么将递归与其右节点对比
            //如果生成了节点之后将其赋值给右边
            currentNode.right = addEle(currentNode.right, e);
        }

        //----此时已经把节点插入到相对应的正确位置了，开始考虑平衡之事------
        //左旋的操作
        //此时的左右节点都代表刚刚新增加的节点
        //如果添加的节点是右节点是红色节点，左节点是黑的
        if (isRed(currentNode.right) && !isRed(currentNode.left)) {
            currentNode = leftRotate(currentNode);
        }

        //右旋
        //如果出现了 红红黑的情况  则需要右旋
        if (isRed(currentNode.left) && isRed(currentNode.left.left)) {
            currentNode = rightRotate(currentNode);
        }

        //颜色翻转
        if (isRed(currentNode.right) && isRed(currentNode.left)) {
            flipColors(currentNode);
        }

        return currentNode;
    }


    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node) {

        Node x = node.left;

        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node) {
        //由于我们是个左倾红黑树，红色节点如果在右边，需要左旋
        Node x = node.right;
        node.right=x.left;
        x.left=node;
        //然后改变颜色
        x.color=node.color;
        node.color=RED;
        return x;
    }

    private boolean isRed(Node node) {
        if (node == null) return BLACK;
        return node.color;
    }


    /**
     * 红黑树的节点
     */
    private class Node {
        public E e;

        /**
         * 左节点
         */
        public Node left;
        /**
         * 右节点
         */
        public Node right;
        /**
         * 节点颜色
         */
        public boolean color;

        /**
         * 默认构造节点颜色为红色
         * @param e
         */
        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
            this.color = RED;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    ", color=" + color +
                    '}';
        }
    }


    /**
     * 层次遍历树
     */
    public void levelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node curNode = queue.remove();
            System.out.println(curNode);
            if (curNode.left != null) {
                queue.add(curNode.left);
            }
            if (curNode.right != null) {
                queue.add(curNode.right);
            }
        }
    }
}
