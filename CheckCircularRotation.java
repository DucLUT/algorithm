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

// import java.lang.*;
// import java.util.concurrent.*;


public class CheckCircularRotation{
    public static void main(String[] args) {
        String s = args[0];
        String t = args[1];
        boolean checked = false;
        if (s.length() == t.length()){
            String temp = s + s;
            checked = temp.indexOf(t) != -1;
        }
        System.out.println(checked);
    }
}