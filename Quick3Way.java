public import edu.princeton.cs.algs4.StdRandom;
public class Quick3Way {
    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }
    private static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo){
            return;
        }
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt){
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }
        
        sort(a, lo, lt - 1);
        sort(a,j+1,hi);

    }
    private static int partition(Comparable[] a, int lo, int hi){
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true){
            while (less(a[i--], v)) if (i == hi) break;
            while (less(v, a[j--])) if (j == lo) break;
            if (i >= j){
                break;
            }
            exch(a, i, j);  
        }
        exch(a, lo, j);
        return j;
    }
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    public static boolean check(Comparable[] a){
        sort(a);
        for (int i = 1; i < a.length; i++){
            if (less(a[i], a[i-1])){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Integer[] a = { 3, 4, 33, 432, 34, 3333 };
        sort(a);
        show(a);
    }
} {
    
}
