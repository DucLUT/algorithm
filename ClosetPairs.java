import java.util.*;
public class ClosetPairs {
    public static double[] findClosestPair(double[] a){
        if (a == null || a.length < 2){
            throw new IllegalArgumentException("Array only has 2 elements");
        }
        Arrays.sort(a);
        double minDiff = Double.MAX_VALUE;
        double[] closestPair = new double[2];
        for (int i = 0; i<a.length -1; i++){
            double diff = Math.abs(a[i + 1] - a[i]);
            if (diff < minDiff){
                minDiff = diff;
                closestPair[0] = a[i];
                closestPair[1] = a[i+1];
            }
        }
        return closestPair;
    }

    public static void main(String[] args) {
        double[] a = {1.0, 3.5, 4.1, 2.8, 5.9}; 
        double[] closestPair = findClosestPair(a);
        System.out.println("Closest pair: " + closestPair[0] + ", " + closestPair[1]);
    }

}
