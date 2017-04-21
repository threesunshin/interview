/*
链表相关, 有头结点
 */
public class Link{
    Node head;
    int size;
    Node current;
    public Link(){
        this.head = new Node(null);
        this.current = this.head;
        this.size = 0;
    }

    public void index(int index) throws Exception{
        /*
        定位到要操作元素那里
         */ 
        if( index < -1 || index > size){
            throw new Exception("wrong index.");
        }
        if( index == -1)
            return; // 在头结点之后操作，第一个元素下标是0，head下标就是-1
        current = head.getNextNode();
        while( current != null && index > 0){
            current = current.getNextNode();
            index--;
        }
    }

    public void delete(int index) throws Exception{
        if( isEmpty()){
            throw new Exception("link is empty, cannot delete.");
        }
        if( index < 0 || size < index)
            throw new Exception("wrong index.");
        index(index-1);
        current.setNextNode(current.getNextNode().getNextNode());
        size--;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size==0?true:false;
    }

    public void insert(int value, int index) throws Exception{
        if( index <  0 || size < index){
            throw new Exception("wrong index.");
        }
        index(index-1);
        current.setNextNode(new Node(value, current.getNextNode()));
        size++;
    }
    public void reverse(){
        /*
        单向链表反转
         */
        if( size == 1)
            return;
        Node pre = head;
        Node cur = pre.getNextNode();
        pre.setNextNode(null);
        while( cur != null){
            Node tmp = cur.getNextNode();
            cur.setNextNode(pre);
            pre = cur;
            cur = tmp;
        }
        head = pre;
    }
    public boolean isCircle(){
        /*
        判断链表是否有环
         */
        if( size < 2)
            return false;
        Node slow = head;
        Node fast = head;
        while( slow != null && fast != null){
            slow = slow.getNextNode();
            fast = fast.getNextNode().getNextNode();
            if( slow == fast){
                return true;
            }
        }
        return false;
    }

    public void printLink(){
        
        if( isEmpty() )
            return;
        Node node = head.getNextNode();
        while( node != null){
            System.out.println(node.getValue());
            node = node.getNextNode();
        }
    }

    public static void main(String[] args) throws Exception{
        Link link = new Link();
        link.insert(1,0);
        link.insert(2,1);

        //Node node1 = new Node(3);
        link.insert(3,2);
        //Node node2 = new Node(5);
        //link.insert(node2,3);
        link.printLink();
        //System.out.println(link.isCircle());
        //link.reverse();
        link.delete(1);
        link.printLink();
        //System.out.println(link.getSize());
    }
}
class Node{
    private int value;
    private Node next;

    public Node(int value){
        this(value,null);
    }

    public Node(Node next){
        this.next = next;
    }
    public Node(int value, Node next){
        this.value = value;
        this.next = next;
    }

    public Node getNextNode(){
        return this.next;
    }

    public void setNextNode(Node node){
        this.next = node;
    }

    public int getValue(){
        return this.value;
    }
}