
public class LinkedList <T> {
    Node head=null;
    Node next=null;
    Node tail=null;
   
    int size=0;

     public void insert(T data){
         Node node=new Node(); 
         node.data=data; 
     if(head!=null){  
        Node<T> n=head; 
        while(n.next!=null){  
             n=n.next;
         }
       n.next=node; 
     }
     else{
         head=node;
     }
 }
     public void show(){
         Node node=head;
         while(node!=null){
             System.out.print(node.data +" ");
             node=node.next;
         }
         System.out.println(node +">>");
     }
     
     public int getSize(){
         size=0;
         Node n=head;
         while(n!=null){
                size++;
             n=n.next;

         }

         return size;


     }
     public void add(T data){
         Node n=new Node();
         n.data=data;
         n.next=head;
         head =n;

 }
     
     public T peek(){
          if(head!=null){
              return (T) head.data;
        }
        else{
            System.out.println("LinkedList is empty...");
            return null;
        }
     }


 public void addLast(T data){
     Node <T> nn=new Node <T> ();
     nn.data=data;
     if(head!=null){
        Node <T> n=head;
         while(n.next!=null){
            n=n.next;

         }
        n.next=nn;
     }
     else{
         head=nn;
     }
     size++;
 }
  public T removeFirst() {
         if (head == null) {
             System.out.println("This LinkedList is null");
             return null;
         } else {
             Node<T> removedNode = head;
             head = head.next;
             size--;
             return removedNode.getData();
         }
     }
 }

 class Node<T>{
     T data;
     Node next;

     public Node(){

     }

     public T getData() {
         return data;
     }

     public void setData(T data) {
         this.data = data;
     }

     public Node getNext() {
         return next;
     }

     public void setNext(Node next) {
         this.next = next;
     }
    
}