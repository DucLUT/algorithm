public class FasterMerge {
    private static Comparable[] aux;
    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a,0, a.length - 1);
    }
    public static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo){
            return;
        }
        int mid = lo + (hi -lo)/2;
        sort(a, lo, mid);
        sort(a, mid+1,hi);
        merge(a, lo, mid, hi);

    }
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        for (int i = lo; i <= mid; i++) {
            aux[i] = a[i];
        }

        for (int j = mid + 1, k = hi; j <= hi; j++, k--) {
            aux[j] = a[k];
        }

        int i = lo, j = hi;
        for (int k = lo; k <= hi; k++) {
            if (less(aux[i], aux[j])) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j--];
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