import java.util.Stack;

public class Stock {
    public static int[] calculateDays(int[] prices) {
        int n = prices.length;
        int[] days = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && prices[i] > prices[stack.peek()]) {
                int index = stack.pop();
                days[index] = i - index;
            }
            stack.push(i);
        }

        // For remaining indices in the stack, there are no future days with a higher price
        while (!stack.isEmpty()) {
            days[stack.pop()] = 0;
        }

        return days;
    }

    public static void main(String[] args) {
        int[] prices = {8, 1, 2, 4, 6, 3, 5};
        int[] days = calculateDays(prices);

        for (int day : days) {
            System.out.print(day + " ");
        }
    }
}