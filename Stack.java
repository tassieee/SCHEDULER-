
public class Stack {
    private String[] elements; 
    private int top;            // index of the stack top
    private int capacity;       // stack max capacity


    public Stack(int capacity) {
        this.capacity = capacity;
        elements = new String[capacity];
        top = -1;              // to indicates the stack is empty
    }

   
    public boolean isFull() {      // check if its full
        return top == capacity - 1;
    }


    public boolean isEmpty() {    // check if its empty
        return top == -1;
    }

    
    public void push(String item) {
        if (isFull()) {
            throw new RuntimeException("Stack is overflowing....");     // throw exception if the stack is alrd full
        }
        else {
            elements[++top] = item;  // to place the item on the top of the stack
        }
    }

    
    public String pop() {       // pops the top item
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty....");    // throw exception if the stack is empty
        }
        else {
            return elements[top--]; 
        }
    }
    
     public String peek(){
        if(!isEmpty()){
            return  elements[top];
        }
        else{
            System.out.println("Stack is empty...");
            return null;
        }
    }
}
