import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;
import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import java.util.Arrays;

import java.lang.*;
import java.util.concurrent.*;
//first exercise


public class First1{
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Point2D[] points = new Point2D[N];
        for (int i = 0; i<N;i++){
            double x = ThreadLocalRandom.current().nextDouble(0,400);
            double y = ThreadLocalRandom.current().nextDouble(0,400);
            points[i] = new Point2D(x,y);
        }
        // Arrays.sort(points, (p1, p2) -> Double.compare(p1.x(), p2.x()));
        System.out.println(findCloset(points));

    }

    static double dist(Point2D p1, Point2D p2) {
        // Corrected Math.pow usage
        double a = Math.sqrt(Math.pow(p1.x() - p2.x(), 2) + Math.pow(p1.y() - p2.y(), 2));
        return a;
    }

    static double findCloset(Point2D[] points) { // Ensure consistent use of Point2D
        double minD = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double currentDist = dist(points[i], points[j]); // Call dist once per pair
                if (currentDist < minD) {
                    minD = currentDist;
                }
            }
        }
        return minD;
    }

}