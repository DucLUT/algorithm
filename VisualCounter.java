// import edu.princeton.cs.algs4.Interval1D;
// import edu.princeton.cs.algs4.Interval2D;
// import edu.princeton.cs.algs4.Counter;
// import edu.princeton.cs.algs4.StdDraw;
// import edu.princeton.cs.algs4.Out;
// import edu.princeton.cs.algs4.In;
// import edu.princeton.cs.algs4.Point2D;
// import java.util.Arrays;
// import edu.princeton.cs.algs4.StdIn;
// import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
// import java.lang.*;
// import java.util.concurrent.*;
public class VisualCounter{
    int count;
    int operations;
    int N;
    int max;
    public VisualCounter(int N, int max){
        this.N = N;
        this.max = max;
        this.count = 0;
        this.operations = 0;
        StdDraw.setXscale(0,N);
        StdDraw.setYscale(0, max*2);
        StdDraw.line(0, max, N, max);

    }
    public void increment() {
        if (this.operations < N && this.count < max) {
            this.operations++;
            this.count++;
            StdDraw.point(this.operations, this.count);
        }
    }

    public void decrement() {
        if (this.operations < N && this.count > 0) {
            this.operations++;
            this.count--;
            StdDraw.point(this.operations, this.count);
        }
    }


    public int tally(){
        return this.count;
    }

    public String toString(){
        return count + "";
    }
    public static void main(String[] args) {
        VisualCounter counter = new VisualCounter(100, 50); // Example values for N and max
        for (int i = 0; i < 50; i++) { // Increment 50 times
            counter.increment();
        }
        for (int i = 0; i < 20; i++) { // Decrement 20 times
            counter.decrement();
        }
    }
    

}