/*
链表相关
 */
public class Link{
    Node head;
    int size;
    public Link(int value){
        this.head = new Node(value);
        this.size = 1;
    }

    public Link(){
        this.head = null;
        this.size = 0;
    }

    public void add(int value){
        if( head == null){
            head = new Node(value);
            size = 1;
        }
        else{
            Node node = head;
            while( node.getNextNode() != null){
                node = node.getNextNode();
            }
            node.setNextNode( new Node(value) );
            size += 1;
        }
        
    }
    public int getSize(){
        return size;
    }

    public void insert(Node node, int index){
        if( head == null || size < index){
            System.out.println("the link length less than index.");
            return;
        }
        Node pre = head;
        Node next = head;
        while( index > 0 && next != null){
            pre = next;
            next = next.getNextNode();
            index--;
        }
        pre.setNextNode(node);
        node.setNextNode(next);
        size += 1;
    }
    public void reverse(){
        /*
        单向链表反转
         */
        if( head == null || size == 1)
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
        if( head == null || size < 2)
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
        Node node = head;
        while( node != null){
            System.out.println(node.getValue());
            node = node.getNextNode();
        }
    }

    public static void main(String[] args){
        Link link = new Link(2);
        link.add(4);
        link.add(6);
        Node node1 = new Node(3);
        link.insert(node1,1);
        //Node node2 = new Node(5);
        //link.insert(node2,3);
        link.printLink();
        //System.out.println(link.isCircle());
        link.reverse();
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