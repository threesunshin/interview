/*
链表相关, 有头结点
 */
public class Link{
    LinkNode head;
    int size;
    LinkNode current;
    public Link(){
        this.head = new LinkNode(null);
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
        current.setNextNode(new LinkNode(value, current.getNextNode()));
        size++;
    }
    public void reverse(){
        /*
        单向链表反转
         */
        if( size == 1)
            return;
        LinkNode pre = head;
        LinkNode cur = pre.getNextNode();
        pre.setNextNode(null);
        while( cur != null){
            LinkNode tmp = cur.getNextNode();
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
        LinkNode slow = head;
        LinkNode fast = head;
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
        LinkNode LinkNode = head.getNextNode();
        while( LinkNode != null){
            System.out.println(LinkNode.getValue());
            LinkNode = LinkNode.getNextNode();
        }
    }

    public static void main(String[] args) throws Exception{
        Link link = new Link();
        link.insert(1,0);
        link.insert(2,1);

        //LinkNode node1 = new LinkNode(3);
        link.insert(3,2);
        //LinkNode node2 = new LinkNode(5);
        //link.insert(node2,3);
        link.printLink();
        //System.out.println(link.isCircle());
        //link.reverse();
        link.delete(1);
        link.printLink();
        //System.out.println(link.getSize());
    }
}
class LinkNode{
    private int value;
    private LinkNode next;

    public LinkNode(int value){
        this(value,null);
    }

    public LinkNode(LinkNode next){
        this.next = next;
    }
    public LinkNode(int value, LinkNode next){
        this.value = value;
        this.next = next;
    }

    public LinkNode getNextNode(){
        return this.next;
    }

    public void setNextNode(LinkNode LinkNode){
        this.next = LinkNode;
    }

    public int getValue(){
        return this.value;
    }
}