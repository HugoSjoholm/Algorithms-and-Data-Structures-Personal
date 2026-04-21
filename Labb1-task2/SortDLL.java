import java.util.Comparator;

public class SortDLL {

    public static <T> void dllInsertionSort(T[] a, Comparator<T> cmp) {
        DoublyLinkedList<T> dll = new DoublyLinkedList<>();

        for (int i = 0; i < a.length; i++) {
            dll.addAtFirstSmaller(a[i], cmp);
        }

        for (int i = 0; i < a.length; i++) {
            a[i] = dll.get(i);
        }
    }

    public static boolean isSorted(Integer[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Integer[] a = {5, 2, 8, 1, 3};
        dllInsertionSort(a, Comparator.naturalOrder());

        System.out.print("Sorterad array: ");
        for (int x : a) {
            System.out.print(x + " ");
        }
        System.out.println();

        System.out.println("Är sorterad: " + isSorted(a));

        int size = 10000;

        Integer[] a1 = TestGenericSort.randomInts(size, -size, size);
        Integer[] a2 = a1.clone();

        double t1 = TestGenericSort.measureTime(
            arr -> dllInsertionSort(arr, Comparator.naturalOrder()), a1);

        double t2 = TestGenericSort.measureTime(
            GenericSorting::insertionSort, a2);

        System.out.println("Tid dllInsertionSort: " + t1 + " s");
        System.out.println("Tid insertionSort: " + t2 + " s");
    }
}