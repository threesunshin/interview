import java.util.Stack;
import java.util.Queue;
import java.util.ArrayDeque;

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
/*
二元查找树，左节点的值 《 根的值 《 右节点的值
 */
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

    public void breadthFirst(){
        //广度优先
        breadthFirst(root);
    }

     public void breadthFirst( Node<T> node){
        if( node == null)
            return;
        Queue<Node<T>> queue = new ArrayDeque<Node<T>>();
        queue.offer(node);
        while( !queue.isEmpty() ){
            Node<T> temp = queue.poll();
            System.out.println(temp.getValue());
            if(temp.getLeft() != null)
                queue.offer( temp.getLeft());
            if(temp.getRight() != null)
                queue.offer( temp.getRight());
        }
     }
//把二元查找树转变成排序的双向链表
//中序遍历，然后到当前节点时，默认之前的已经排好序，当前节点只用指向链表的尾部就行
    private  Node<T> listHeader;
    private  Node<T> listIndex;

     public void inOrderConvert(){
        inOrderConvert(root);
    }
    
    public void inOrderConvert(Node<T> node){
        if( node == null){
            return;
        }
        inOrderConvert(node.getLeft());
        convert(node);
        inOrderConvert(node.getRight());
    }

    public  void convert(Node<T> node){
        node.setLeft(listIndex);
        if(listIndex != null){
            listIndex.setRight(node);
        }
        else{
            listHeader = node;
        }
        listIndex = node;
    }

    public void printLink(){
        Node<T> pNode = listHeader;
        while(pNode != null){
            System.out.println( pNode.getValue());
            pNode = pNode.getRight();
        }
    }
/*
在二元树中找出和为某一值的所有路径，从树的根结点开始往下访问一直到叶结点所经过的所有结点形成一条路径
 */
    public void getPath(Node<T> node, int sum, Stack<Node<T>> stack, int currentSum ){

        if( node == null )
            return;
        stack.push(node);
        currentSum += (Integer)node.getValue();
        if( node.getRight() == null && node.getLeft() == null){
            if( currentSum == sum){
                //print stack
                while( !stack.isEmpty() ){
                    Node<T> temp = stack.pop();
                    System.out.println(temp.getValue());
                }
            }
            else{
                stack.pop();//当前是叶子节点，但是sum不等，要删除叶子节点，回到父节点
                currentSum -= (Integer)node.getValue();
            }
        }

        getPath( node.getLeft(), sum, stack, currentSum);
        getPath( node.getRight(), sum, stack, currentSum);
        
    }

    public void findPath(int sum){
        Stack<Node<T>> stack = new Stack<Node<T>>();
        getPath(root, sum, stack,0);
    }

    public static void main(String[] args){
        BSTree<Integer> tree = new BSTree<Integer>();
        int[] keys = new int[]{7,5,6,2,8};
        for(int key: keys){
            //System.out.println("key is :" + key);
            tree.add(key);
            //System.out.println( "root is:" + tree.getRoot());
        }
        System.out.println( "pre order:");
       //tree.inOrderConvert();
        //tree.printLink();
        //tree.inOrderTraverse();
       
        tree.breadthFirst();
    }
}