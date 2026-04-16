import java.util.Comparator;

public class SortDLL {

    public static <T> void dllInsertionSort(T[] a, Comparator<T> cmp) {
        // TODO
    }

    public static void main(String[] args) {
        Integer[] a = {5, 2, 8, 1, 3};
        dllInsertionSort(a, Comparator.naturalOrder());

        for (Integer x : a) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
}