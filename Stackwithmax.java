import edu.princeton.cs.algs4.Stack;

public class Stackwithmax {
    private Stack<Double> mainStack;
    private Stack<Double> maxStack;

    public MaxStack() {
        mainStack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(double x) {
        mainStack.push(x);
        if (maxStack.isEmpty() || x >= maxStack.peek()) {
            maxStack.push(x);
        } else {
            maxStack.push(maxStack.peek());
        }
    }
    public double pop() {
        if (mainStack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        maxStack.pop();
        return mainStack.pop();
    }

    public double max() {
        if (maxStack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return maxStack.peek();
    }

}
