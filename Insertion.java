import java.util.Arrays;
public class Insertion {
    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++){
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--){
                exch(a, j, j-1);

            }
        }
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
}