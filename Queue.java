
public class Queue<T> {

    private int head;
    private int tail;
    private T[] arr;
    private int maxSize;

    public Queue() {
    }

    public Queue(int maxSize) {
        this.head = 0;
        this.tail = 0;
        this.maxSize = maxSize;
        this.arr = (T[]) new Object[maxSize];
    }

    public boolean isEmpty() {
        return tail == 0;
    }

    public boolean isFull() {
        return tail >= maxSize;
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("The queue is empty...");
            //optimize:
            // or throw an exception
            return null;
        }
        else{
            return arr[head];
        }
    }

    public void enqueue(T input) {
        if (!isFull()) {
            arr[tail] = input;
            //tail++;
            //optimize:
            tail = (tail + 1) % maxSize;
        } else {
            //System.out.println("The queue is full...");
            //Optimize:
            throw new IllegalStateException("The queue is full.");
        }
    }

    public void dequeue() {
        if (isEmpty()) {
            System.out.println("The queue is empty...");
        } else {
            /*
            T temp = arr[head];
            arr[head] = null;
            for (int i = 0; i < maxSize;i++) {
                arr[i] = arr[i+1];
            }
            arr[tail] = null;
            tail--;
             */

            //optimize:
            //Instead of shifting elements in the dequeue method, consider implementing a circular queue. This can be achieved by using the modulo operation (%) when updating the indices.
            arr[head] = null;
            head = (head + 1) % maxSize;
        }
    }

    public void display() {
        if (!isEmpty()) {
            /*
            for (int i = 1; i <= tail; i++) {
                System.out.println("Queue: [" + i + "] is " + arr[i]);
            }
             */
            //optimize:
            for (int i = head; i != tail; i = (i + 1) % maxSize) {
                System.out.println("Queue element: " + arr[i]);
            }
        }
    }

}