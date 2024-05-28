
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class TestFile {
    
    private static boolean isQFirst = true;
    private static boolean isSFirst = true;
    private static boolean isLFirst = true;
    private static long qAtt;
    private static long qArt;
    private static long qAet;
    private static long sAtt;
    private static long sArt;
    private static long sAet;
    private static long lAtt;
    private static long lAet;
    private static long lArt;
    
    public static void main(String[] args){
        String fileName = "tasks.txt";   
        
        System.out.println("Queue:");
        process(fileName, "A"); //queue
        System.out.println("\nLinked List:");
        process(fileName, "B"); //linkedlist
        System.out.println("\nStack:");
        process(fileName, "C"); //stack
    }
    
    public static void process(String fileName, String systemName){
        
        Queue<String> qName = new Queue<>(23);
        Queue<String> qType = new Queue<>(23);
        Queue qInput = new Queue(23);
        
        Stack sName = new Stack(23);
        Stack sType = new Stack(23);
        Stack sInput = new Stack(23);
        
        LinkedList<String> lName = new LinkedList<>();
        LinkedList<String> lType = new LinkedList<>();
        LinkedList<String> lInput = new LinkedList<>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] taskInfo = line.split(" ");
                String name = taskInfo[0];
                String inputType = taskInfo[1];
                String input = taskInfo[2];
                
             
                switch(systemName){
                    case "A": //queue
                        qName.enqueue(name);
                        qType.enqueue(inputType);
                        qInput.enqueue(input);
                        break;
                    case "B": //linkedlist
                        lName.add(name);
                        lType.add(inputType);
                        lInput.add(input);
                        break;
                    case "C": //stack
                        sName.push(name);
                        sType.push(inputType);
                        sInput.push(input);
                       
                        break;
                    default:
                        break;
                }       
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        Task task = new Task();
        switch(systemName){
            
            case "A":
                
                task.setArrivalTime(System.nanoTime());
                queueStarter(qName, qInput, task.getArrivalTime());
                break;
            case "B":
                
                task.setArrivalTime(System.nanoTime());
                linkedListStarter(lName, lInput, task.getArrivalTime());
                break;
            case "C":
                task.setArrivalTime(System.nanoTime());
                stackStarter(sName, sInput, task.getArrivalTime());
                break;
            default:
                break;
        }
    }
    
    public static void queueStarter(Queue<String> q, Queue qIn, long arrivalTime) {
        long totalResponseTime = 0;
        long totalTurnaroundTime = 0;
        long totalExecutionTime = 0;
        int totalTasks = 0;

        System.out.println("Queue = Scheduling System A");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-20s | %-10s | %-60s | %-60s |%n", "Task", "Type", "Input", "Result");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (int i = 1; i < 23; i++) {
            long startTime = System.nanoTime();

            switch (q.peek()) {
                case "isPrime": {
                    long num = Long.parseLong((String) qIn.peek());
                     System.out.printf("| %-20s | %-10s | %-60d | %-60s |%n", "isPrime", "int", num, StarterPack.isPrime(num));
                        q.dequeue();
                    qIn.dequeue();
                    break;
                }
                case "fib": {
                    int num = Integer.parseInt((String) qIn.peek());
                    System.out.printf("| %-20s | %-10s | %-60d | %-60s |%n", "fib", "int", num, StarterPack.fib(num));
                    q.dequeue();
                    qIn.dequeue();
                    break;
                }
                case "getNthUglyNo": {
                    int num = Integer.parseInt((String) qIn.peek());
                    System.out.printf("| %-20s | %-10s | %-60d | %-60s |%n", "getNthUglyNo", "int", num, StarterPack.getNthUglyNo(num));
                    q.dequeue();
                    qIn.dequeue();
                    break;
                }
                case "longestPalSubstr": {
                    String input = (String) qIn.peek();
                    System.out.printf("| %-20s | %-10s | %-60s | %-60s |%n", "longestPalSubstr", "String", input, StarterPack.longestPalSubstr(input));
                    q.dequeue();
                    qIn.dequeue();
                    break;
                }
                default: {
                    int num = Integer.parseInt((String) qIn.peek());
                    System.out.printf("| %-20s | %-10s | %-60d | %-60s |%n", "DefaultMethod", "int", num, StarterPack.sumOfDigitsFrom1ToN(num));
                    q.dequeue();
                    qIn.dequeue();
                    break;
                }
            }
            long endTime = System.nanoTime();

            // Calculate response time and turnaround time
            long responseTime = startTime - arrivalTime;
            long turnaroundTime = endTime - arrivalTime;
            long executionTime = endTime - startTime;

            // Accumulate times for averaging
            totalResponseTime += responseTime;
            totalTurnaroundTime += turnaroundTime;
            totalExecutionTime += executionTime;
            totalTasks++;
        }
         System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        if(isQFirst == true){
            // Calculate averages
            qArt = totalTasks > 0 ? (long) totalResponseTime / totalTasks : 0;
            qAtt = totalTasks > 0 ? (long) totalTurnaroundTime / totalTasks : 0;
            qAet = totalTasks > 0 ? (long) totalExecutionTime / totalTasks : 0;
           
            
            System.out.println("Average Response Time: " + qArt + " nanoseconds");
            System.out.println("Average Turnaround Time: " + qAtt + " nanoseconds");
            System.out.println("Average Execution Time: " + qAet + " nanoseconds");
            isQFirst = false;
        }
        else{
            // Print reports
            System.out.println("Average Response Time: " + qArt + " nanoseconds");
            System.out.println("Average Turnaround Time: " + qAtt + " nanoseconds");
            System.out.println("Average Execution Time: " + qAet + " nanoseconds");
        }
        
        
    }

    public static void linkedListStarter(LinkedList l, LinkedList lIn, long arrivalTime) {
        long totalResponseTime = 0;
        long totalTurnaroundTime = 0;
        long totalExecutionTime = 0;
        int totalTasks = 0;

        System.out.println("Linked List = Scheduling System B");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-20s | %-10s | %-60s | %-60s |%n", "Task", "Type", "Input", "Result");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < 22; i++) {
            long startTime = System.nanoTime();

            switch ((String) l.removeFirst()) {
                case "isPrime": {
                    long num = Long.parseLong((String) lIn.peek());
                    System.out.printf("| %-20s | %-10s | %-60d | %-60s |%n", "isPrime", "int", num, StarterPack.isPrime(num));
                    lIn.removeFirst();
                    break;
                }
                case "fib": {
                    int num = Integer.parseInt((String) lIn.peek());
                    System.out.printf("| %-20s | %-10s | %-60d | %-60s |%n", "fib", "int", num, StarterPack.fib(num));
                    lIn.removeFirst();
                    break;
                }
                case "getNthUglyNo": {
                    int num = Integer.parseInt((String) lIn.peek());
                    System.out.printf("| %-20s | %-10s | %-60d | %-60s |%n", "getNthUglyNo", "int", num, StarterPack.getNthUglyNo(num));
                    lIn.removeFirst();
                    break;
                }
                case "longestPalSubstr": {
                    String input = (String) lIn.peek();
                    System.out.printf("| %-20s | %-10s | %-60s | %-60s |%n", "longestPalSubstr", "String", input, StarterPack.longestPalSubstr(input));
                    lIn.removeFirst();
                    break;
                }
                default: {
                    int num = Integer.parseInt((String) lIn.peek());
                    System.out.printf("| %-20s | %-10s | %-60d | %-60s |%n", "DefaultMethod", "int", num, StarterPack.sumOfDigitsFrom1ToN(num));
                    lIn.removeFirst();
                    break;
                }
            }
            long endTime = System.nanoTime();

            // Calculate response time and turnaround time
            long responseTime = startTime - arrivalTime;
            long turnaroundTime = endTime - arrivalTime;
            long executionTime = endTime - startTime;

            // Accumulate times for averaging
            totalResponseTime += responseTime;
            totalTurnaroundTime += turnaroundTime;
            totalExecutionTime += executionTime;
            totalTasks++;
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");


        if(isLFirst == true){
            // Calculate averages
            lArt = totalTasks > 0 ? (long) totalResponseTime / totalTasks : 0;
            lAtt = totalTasks > 0 ? (long) totalTurnaroundTime / totalTasks : 0;
            lAet = totalTasks > 0 ? (long) totalExecutionTime / totalTasks : 0;

            // Print reports
            System.out.println("Average Response Time: " + lArt + " nanoseconds");
            System.out.println("Average Turnaround Time: " + lAtt + " nanoseconds");
            System.out.println("Average Execution Time: " + lAet + " nanoseconds");
            isLFirst = false;
        }
        else{
            // Print reports
            System.out.println("Average Response Time: " + lArt + " nanoseconds");
            System.out.println("Average Turnaround Time: " + lAtt + " nanoseconds");
            System.out.println("Average Execution Time: " + lAet + " nanoseconds");
        }
    }

    public static void stackStarter(Stack s, Stack sIn, long arrivalTime) {
        long totalResponseTime = 0;
        long totalTurnaroundTime = 0;
        long totalExecutionTime = 0;
        int totalTasks = 0;

        System.out.println("Stack = Scheduling System C");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-20s | %-10s | %-60s | %-60s |%n", "Task", "Type", "Input", "Result");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < 22; i++) {
            long startTime = System.nanoTime();

            switch (s.peek()) {
                case "isPrime": {
                    long num = Long.parseLong((String) sIn.peek());
                    System.out.printf("| %-20s | %-10s | %-60d | %-60s |%n", "isPrime", "int", num, StarterPack.isPrime(num));
                        s.pop();
                    sIn.pop();
                    break;
                }
                case "fib": {
                    int num = Integer.parseInt((String) sIn.peek());
                    System.out.printf("| %-20s | %-10s | %-60d | %-60s |%n", "fib", "int", num, StarterPack.fib(num));
                    s.pop();
                    sIn.pop();
                    break;
                }
                case "getNthUglyNo": {
                    int num = Integer.parseInt((String) sIn.peek());
                    System.out.printf("| %-20s | %-10s | %-60d | %-60s |%n", "getNthUglyNo", "int", num, StarterPack.getNthUglyNo(num));
                    s.pop();
                    sIn.pop();
                    break;
                }
                case "longestPalSubstr": {
                    String input = (String) sIn.peek();
                    System.out.printf("| %-20s | %-10s | %-60s | %-60s |%n", "longestPalSubstr", "String", input, StarterPack.longestPalSubstr(input));
                     s.pop();
                    sIn.pop();
                    break;
                }
                default: {
                    int num = Integer.parseInt((String) sIn.peek());
                    System.out.printf("| %-20s | %-10s | %-60d | %-60s |%n", "DefaultMethod", "int", num, StarterPack.sumOfDigitsFrom1ToN(num));
                    s.pop();
                    sIn.pop();
                    break;
                }
            }
            long endTime = System.nanoTime();
            
            // Calculate response time and turnaround time
            long responseTime = startTime - arrivalTime;
            long turnaroundTime = endTime - arrivalTime;
            long executionTime = endTime - startTime;

            // Accumulate times for averaging
            totalResponseTime += responseTime;
            totalTurnaroundTime += turnaroundTime;
            totalExecutionTime += executionTime;
            totalTasks++;
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        if(isSFirst == true){
            // Calculate averages
            sArt = totalTasks > 0 ? (long) totalResponseTime / totalTasks : 0;
            sAtt = totalTasks > 0 ? (long) totalTurnaroundTime / totalTasks : 0;
            sAet = totalTasks > 0 ? (long) totalExecutionTime / totalTasks : 0;
            

            // Print reports
            System.out.println("Average Response Time: " + sArt + " nanoseconds");
            System.out.println("Average Turnaround Time: " + sAtt + " nanoseconds");
            System.out.println("Average Execution Time: " + sAet + " nanoseconds");
            isSFirst = false;
        }
        else{

            // Print reports
            System.out.println("Average Response Time: " + sArt + " nanoseconds");
            System.out.println("Average Turnaround Time: " + sAtt + " nanoseconds");
            System.out.println("Average Execution Time: " + sAet + " nanoseconds");
        }
    }
    
    public static void compare(){
        System.out.println("Comparison between scheduling system");
        
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        System.out.println("Queue = Scheduling System A");
        System.out.println("Average Response Time: " + qArt + " nanoseconds");
        System.out.println("Average Turnaround Time: " + qAtt + " nanoseconds");
        System.out.println("Average Execution Time: " + qAet + " nanoseconds");
        
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        System.out.println("Linked List = Scheduling System B");
        System.out.println("Average Response Time: " + lArt + " nanoseconds");
        System.out.println("Average Turnaround Time: " + lAtt + " nanoseconds");
        System.out.println("Average Execution Time: " + lAet + " nanoseconds");
        
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
       
        System.out.println("Stack = Scheduling System C");
        System.out.println("Average Response Time: " + sArt + " nanoseconds");
        System.out.println("Average Turnaround Time: " + sAtt + " nanoseconds");
        System.out.println("Average Execution Time: " + sAet + " nanoseconds");
        
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        // Compare times
        String best;
        if (qAet < lAet && qAet < sAet) {
            best = "Queue = Scheduling System A";
        } else if (lAet < qAet && lAet < sAet) {
            best = "Linked List = Scheduling System B";
        } else {
            best = "Stack = Scheduling System C";
        }
        System.out.println("Best Scheduling System: " + best);
    }
    
    public static void testCom(){
        // Calculate time differences
        long queueToLinkedListDiff = lAet - qAet;
        long queueToStackDiff = sAet - qAet;
        long linkedListToStackDiff = sAet - lAet;

        // Corrected to use absolute values
        queueToLinkedListDiff = Math.abs(queueToLinkedListDiff);
        queueToStackDiff = Math.abs(queueToStackDiff);
        linkedListToStackDiff = Math.abs(linkedListToStackDiff);

        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Time Difference (Queue to LinkedList): " + queueToLinkedListDiff + " nanoseconds");
        System.out.println("Time Difference (Queue to Stack): " + queueToStackDiff + " nanoseconds");
        System.out.println("Time Difference (LinkedList to Stack): " + linkedListToStackDiff + " nanoseconds");
    }
}