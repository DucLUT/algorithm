import edu.princeton.cs.algs4.Stack;

public class QueueStack<T> {
    private int N;
    private Stack<T> stack1 = new Stack<>();
    private Stack<T> stack2 = new Stack<>();
    
    public void enqueue(T item) {
        stack1.push(item);
        N++;
    }
    
    public T dequeue() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (!stack2.isEmpty()) {
            N--;
            return stack2.pop();
        }
        return null;  // Consider throwing an exception for an empty queue.
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    public static void main(String[] args) {
        // Test with Integer
        QueueStack<Integer> integerQueue = new QueueStack<>();
        integerQueue.enqueue(1);
        integerQueue.enqueue(2);
        integerQueue.enqueue(3);
        System.out.println("Integer queue:");
        while (!integerQueue.isEmpty()) {
            System.out.println(integerQueue.dequeue());
        }

        // Test with String
        QueueStack<String> stringQueue = new QueueStack<>();
        stringQueue.enqueue("one");
        stringQueue.enqueue("two");
        stringQueue.enqueue("three");
        System.out.println("String queue:");
        while (!stringQueue.isEmpty()) {
            System.out.println(stringQueue.dequeue());
        }

        // Test with Custom Object
        QueueStack<Person> personQueue = new QueueStack<>();
        personQueue.enqueue(new Person("John", 30));
        personQueue.enqueue(new Person("Jane", 25));
        personQueue.enqueue(new Person("Doe", 40));
        System.out.println("Person queue:");
        while (!personQueue.isEmpty()) {
            System.out.println(personQueue.dequeue());
        }
    }
}

class Person {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}
