import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreeSumVisualization {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<Point> points = generatePoints(nums);
        Set<List<Integer>> collinearTriples = findCollinearTriples(points);

        drawPointsAndLines(points, collinearTriples);
    }

    private static List<Point> generatePoints(int[] nums) {
        List<Point> points = new ArrayList<>();
        for (int num : nums) {
            points.add(new Point(num, num * num * num));
        }
        return points;
    }

    private static Set<List<Integer>> findCollinearTriples(List<Point> points) {
        Set<List<Integer>> result = new HashSet<>();
        int n = points.size();

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (areCollinear(points.get(i), points.get(j), points.get(k))) {
                        List<Integer> triplet = new ArrayList<>();
                        triplet.add(points.get(i).x);
                        triplet.add(points.get(j).x);
                        triplet.add(points.get(k).x);
                        triplet.sort(Integer::compareTo);
                        result.add(triplet);
                    }
                }
            }
        }

        return result;
    }

    private static boolean areCollinear(Point p1, Point p2, Point p3) {
        return (p2.y - p1.y) * (p3.x - p2.x) == (p3.y - p2.y) * (p2.x - p1.x);
    }

    private static void drawPointsAndLines(List<Point> points, Set<List<Integer>> collinearTriples) {
        StdDraw.setScale(-5, 5);
        StdDraw.setPenRadius(0.01);

        
        for (Point point : points) {
            StdDraw.point(point.x, point.y);
        }

        
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.RED);
        for (List<Integer> triplet : collinearTriples) {
            double[] x = new double[3];
            double[] y = new double[3];
            for (int i = 0; i < 3; i++) {
                x[i] = triplet.get(i);
                y[i] = Math.pow(triplet.get(i), 3);
            }
            StdDraw.line(x[0], y[0], x[1], y[1]);
            StdDraw.line(x[1], y[1], x[2], y[2]);
        }
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
} 
    

