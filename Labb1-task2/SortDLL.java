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

    public static void main(String[] args) {
        Integer[] a = {5, 2, 8, 1, 3};
        dllInsertionSort(a, Comparator.naturalOrder());

        for (Integer x : a) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
}