import java.util.Stack;


class Node<T extends Comparable<T>>{
    private T value;
    private Node<T> left;
    private Node<T> right;

    
    public Node(Node<T> left, Node<T> right, T value){
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public Node(T value){
        this(null, null, value);
    }

    public Node<T> getLeft(){
        return left;
    }

    public Node<T> getRight(){
        return right;
    }

    public void setLeft( Node<T> left){
        this.left = left;
    }

    public void setRight( Node<T> right){
        this.right = right;
    }

    public T getValue(){
        return value;
    }

}

public class BSTree<T extends Comparable<T>>{
    private Node<T> root;
    public BSTree(){

    }

    public BSTree(Node<T> root){
        this.root = root;
    }

    public T getRoot(){
        return root.getValue();
    }

    public Node<T> add(T value){
        Node<T> newNode = new Node(value);
        if( root == null){
            root = newNode;
            return root;
        }
        Node<T> pNode = root;
        Node<T> parentNode = null;
        while( pNode != null){
            parentNode = pNode;
            if( value.compareTo( pNode.getValue()) > 0){
                pNode = pNode.getRight();
            }
            else{
                pNode = pNode.getLeft();
            }

        }
        if(  value.compareTo( parentNode.getValue()) > 0){
            parentNode.setRight(newNode);
        }
        else{
            parentNode.setLeft(newNode);
        }
        return root;
    }

    public void preOrder(){
        preOrder(this.root);
    }

    public void preOrder(Node<T> node){
        if( node == null ){
            return;
        }
        if( node != null ){
            System.out.println(node.getValue());
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    public void preOrderTraverse(){
        Stack<Node<T>> stack = new Stack<Node<T>>();
        Node<T> node = root;
        while( node != null || !stack.isEmpty()){
            while( node != null){
                System.out.println(node.getValue());
                stack.push(node);
                node = node.getLeft();
            }
            node = stack.pop();
            node = node.getRight();
        }

    }

    public void inOrder(){
        inOrder(root);
    }
    public void inOrder(Node<T> node){
        
        if( node == null)
            return;
        inOrder(node.getLeft());
        System.out.println(node.getValue());
        inOrder(node.getRight());
    }

    public void inOrderTraverse(){
        Stack<Node<T>> stack = new Stack<Node<T>>();
        Node<T> pNode = root;
        while( pNode != null || !stack.isEmpty()){
            while( pNode != null){
                stack.push( pNode);
                pNode = pNode.getLeft();
            }
            Node<T> node = stack.pop();
            System.out.println( node.getValue());
            pNode = node.getRight();
        }
    }

    public void postOrderTraverse(){
        Stack<Node<T>> stack = new Stack<Node<T>>();
        Node<T> preNode = null; //表示最近一次访问的节点
        Node<T> node = root;
        while( node != null || !stack.isEmpty()){
            while( node != null ){
                stack.push(node);
                node = node.getLeft();
            }
            node = stack.peek();

            if( node.getRight() == null || preNode == node.getRight()){
                System.out.println( node.getValue());
                node = stack.pop();
                preNode = node;
                node = null;
            }
            else{
                node = node.getRight();
            }
        }
    }

    public static void main(String[] args){
        BSTree<Integer> tree = new BSTree<Integer>();
        int[] keys = new int[]{7,5,6,2};
        for(int key: keys){
            //System.out.println("key is :" + key);
            tree.add(key);
            //System.out.println( "root is:" + tree.getRoot());
        }
        System.out.println( "pre order:");
        tree.postOrderTraverse();
        //tree.inOrderTraverse();
    }
}