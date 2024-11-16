import edu.princeton.cs.algs4.StdDraw;

public class SortVisualizer {

    // Visualize the array
    public static void visualize(Comparable[] a, int currentIndex, int comparedIndex) {
        int N = a.length;
        StdDraw.clear();
        for (int i = 0; i < N; i++) {
            if (i == currentIndex) {
                StdDraw.setPenColor(StdDraw.RED); // Highlight the current element
            } else if (i == comparedIndex) {
                StdDraw.setPenColor(StdDraw.BLUE); // Highlight the compared element
            } else {
                StdDraw.setPenColor(StdDraw.BLACK); // Default color
            }
            double height = ((Double) a[i]) / 2.0; // Scale height based on value
            StdDraw.filledRectangle(i * 1.0 / N, height / 2.0, 0.4 / N, height / 2.0);
        }
        StdDraw.show();
        StdDraw.pause(50); // Pause for animation
    }

    // General function to visualize sorting
    public static void visualizeInsertion(Comparable[] a) {
        int N = a.length;
        StdDraw.setCanvasSize(800, 400);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        StdDraw.enableDoubleBuffering();

        // Insertion Sort for demonstration
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                // Swap elements
                exch(a, j, j - 1);
                // Visualize the change
                visualize(a, j, j - 1);
            }
        }

        // Final sorted visualization
        visualize(a, -1, -1); // No highlights
    }

    // General function to visualize sorting
    public static void visualizeSelection(Comparable[] a) {
        StdDraw.setCanvasSize(800, 400);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        StdDraw.enableDoubleBuffering();

        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                    visualize(a,i, min);
                }
            }
            exch(a, i, min);
        }

        // Final sorted visualization
        visualize(a, -1, -1); // No highlights
    }
    public static void visualizeShell(Comparable[] a) {
        StdDraw.setCanvasSize(800, 400);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        StdDraw.enableDoubleBuffering();
    
        int n = a.length;
        int h = 1;
    
        // Initialize h to the largest increment less than n/3
        while (h < n / 3) {
            h = h * 3 + 1;
        }
    
        // Shell Sort with visualization
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    visualize(a, j - h, j);
                    exch(a, j, j - h);
                }
            }
            h = h / 3; // Reduce increment
        }
    
        // Final sorted visualization
        visualize(a, -1, -1); // No highlights
    }
    

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        // Example usage
        int N = 100; // Number of elements
        Double[] array = new Double[N];

        // Generate random values
        for (int i = 0; i < N; i++) {
            array[i] = Math.random();
        }

        // Visualize the sorting process
        visualizeShell(array);
    }
}
