import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;
import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.lang.*;
import java.util.concurrent.*;

public class First2 {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Interval1D[] intervals = new Interval1D[N];
        for (int i = 0; i < N; i++){
            StdOut.println("left point of the interval");
            double left = StdIn.readDouble();
            StdOut.println("right point of the interval");
            double right = StdIn.readDouble();
            intervals[i] = new Interval1D(left,right);

        }
        for (int i = 0; i < N; i++){
            for (int j = i+1; j < N; j++){
                if (intervals[i].intersects(intervals[j])){
                    StdOut.println(intervals[i] + " intersects with " + intervals[j]);
                }
            }
        }

    }
    
}
