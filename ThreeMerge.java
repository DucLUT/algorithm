public class ThreeMerge {

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length]; 
        sort(a, 0, a.length - 1); 
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int mid1 = lo + (hi - lo) / 3;
        int mid2 = mid1 + (hi - lo) / 3;

        sort(a, lo, mid1);
        sort(a, mid1 + 1, mid2);
        sort(a, mid2 + 1, hi);

        merge(a, lo, mid1, mid2, hi);
    }


    public static void merge(Comparable[] a, int lo, int mid1, int mid2, int hi) {
        int i = lo, j = mid1 + 1, k = mid2 + 1, t = lo;

        for (int v = lo; v <= hi; v++) {
            aux[v] = a[v];
        }

        while (i <= mid1 && j <= mid2 && k <= hi) {
            if (less(aux[i], aux[j])) {
                if (less(aux[i], aux[k])) {
                    a[t++] = aux[i++];
                } else {
                    a[t++] = aux[k++];
                }
            } else {
                if (less(aux[j], aux[k])) {
                    a[t++] = aux[j++];
                } else {
                    a[t++] = aux[k++];
                }
            }
        }

        while (i <= mid1 && j <= mid2) {
            a[t++] = less(aux[i], aux[j]) ? aux[i++] : aux[j++];
        }
        while (j <= mid2 && k <= hi) {
            a[t++] = less(aux[j], aux[k]) ? aux[j++] : aux[k++];
        }
        while (i <= mid1 && k <= hi) {
            a[t++] = less(aux[i], aux[k]) ? aux[i++] : aux[k++];
        }

        while (i <= mid1) {
            a[t++] = aux[i++];
        }
        while (j <= mid2) {
            a[t++] = aux[j++];
        }
        while (k <= hi) {
            a[t++] = aux[k++];
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }


    public static boolean check(Comparable[] a) {
        sort(a);
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Integer[] arr = {9, 7, 3, 5, 1, 2, 4, 6, 8};
        System.out.println("Original Array:");
        show(arr);

        sort(arr);

        System.out.println("Sorted Array:");
        show(arr);
    }
}
