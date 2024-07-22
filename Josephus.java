import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;
//explain N is the number of the circle and K is the numberth to skip to k*ll
public class Josephus {
    public static int getJosephusNumber(int N, int K){
        Queue<Integer> queue = new Queue<Integer>();
        for (int i = 1 ; i < N + 1 ; i++){
            queue.enqueue(i);
        }
        while (queue.size() > 1){
            for (int i = 0; i < K - 1; i++){
                queue.enqueue(queue.dequeue()); 
            }
            queue.dequeue();

        }
        return queue.peek();
    }
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int K = Integer.parseInt(args[1]);
        StdOut.println("The Josephus number is " + getJosephusNumber(N, K));
    }
}
