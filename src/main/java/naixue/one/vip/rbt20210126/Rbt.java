package naixue.one.vip.rbt20210126;

import com.sun.org.apache.regexp.internal.RE;
import org.omg.CORBA.NO_IMPLEMENT;

import java.util.LinkedList;

public class Rbt<T extends Comparable<T>> {

    private static Boolean RED=Boolean.TRUE;
    private static Boolean BLACK=Boolean.FALSE;

    Node<T> root;

    public void addEle(T val){
       root= addEle(root,val);
       root.color=BLACK;
    }

    private Node<T> addEle(Node<T> node, T val) {

        if (node==null){
            return new Node<>(val);
        }

        if (val.compareTo(node.val)<0){
            node.left=addEle(node.left,val);
        }else {
            node.right=addEle(node.right,val);
        }

        if (!isRed(node.left)&&isRed(node.right)){
            node=leftRote(node);
        }
        if (isRed(node.left)&&isRed(node.left.left)){
            node=rightRote(node);
        }
        if (isRed(node.left)&&isRed(node.right)){
            flipColor(node);
        }
        return node;
    }

    private void flipColor(Node<T> node) {
         node.right.color=BLACK;
        node.left.color=BLACK;
        node.color=RED;
    }

    private Node<T> rightRote(Node<T> node) {
        Node<T> x = node.left;
        node.left=x.right;
        x.right=node;
        x.color=node.color;
        node.color=RED;
        return x;
    }

    private Node<T> leftRote(Node<T> node) {
        Node<T> x = node.right;
        node.right=x.left;
        x.left=node;
        x.color=node.color;
        node.color=RED;
        return x;
    }

    private boolean isRed(Node<T> node) {
        if (node==null){
            return BLACK;
        }
        return node.color;
    }

    public void preOrder(){
        LinkedList<Node<T>> stack = new LinkedList<>();
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


    public static class Node<T>{
        T val;
        Node<T> left;
        Node<T> right;
        Boolean color;

        public Node(T val) {
            this.val = val;
            this.color= RED;
        }
    }
}
