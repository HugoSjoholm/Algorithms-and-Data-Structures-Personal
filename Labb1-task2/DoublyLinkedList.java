import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements Iterable<T> {

    private class ListNode {
        T value;
        ListNode prev;
        ListNode next;

        ListNode(T value) {
            this.value = value;
        }
    }

    private ListNode head;
    private ListNode tail;
    private int size;

    // Create an empty list.
    public DoublyLinkedList() {
        // TODO
    }

    // Add t to the end of the list.
    public void add(T t) {
        // TODO
    }

    // Add t at location index in the list.
    public void add(int index, T t) {
        // TODO
    }

    // Returns the value at location index.
    public T get(int index) {
        // TODO
        return null;
    }

    // Returns the first value in the list.
    public T getFirst() {
        // TODO
        return null;
    }

    // Returns the last value in the list.
    public T getLast() {
        // TODO
        return null;
    }

    /*
     * If the value t exists, all occurrences of t are removed.
     * The number of occurrences removed is returned.
     * If nothing is removed, 0 is returned.
     */
    public int remove(T t) {
        // TODO
        return 0;
    }

    // Removes the value at position index and returns that value.
    public T remove(int index) {
        // TODO
        return null;
    }

    // Removes and returns the last value in the list.
    public T removeLast() {
        // TODO
        return null;
    }

    // Removes and returns the first value in the list.
    public T removeFirst() {
        // TODO
        return null;
    }

    // Checks if the list is empty.
    public boolean isEmpty() {
        // TODO
        return false;
    }

    // Returns the size of the list.
    public int size() {
        // TODO
        return 0;
    }

    // Removes all elements in the list. Makes it empty.
    public void clear() {
        // TODO
    }

    // Add t after the first value smaller than t when scanning backwards.
    public void addAtFirstSmaller(T t, Comparator<T> cmp) {
        // TODO
    }

    @Override
    public String toString() {
        // TODO
        return "";
    }

    @Override
    public Iterator<T> iterator() {
        // TODO
        return null;
    }

    // Optional for checking ---
    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }
}