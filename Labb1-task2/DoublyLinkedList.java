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
        size = 0;
        head = new ListNode(null);
        tail = new ListNode(null);
    }

    // Add t to the end of the list.
    public void add(T t) {
        ListNode newNode = new ListNode(t);

        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        size++;
    }

    // Add t at location index in the list.
    public void add(int index, T t) {
        //checks for valid index
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        //handles case for when index is the last element/tail
        if (index == size) {
            add(t);
            return;
        }

        ListNode newNode = new ListNode(t);


        if (index == 0) {
            newNode.next = head;

            if (size == 0) {
                tail = newNode;
            } else {
                head.prev = newNode;
            }

            head = newNode;
        }
        else {
            //traverse the list untill reaching index node
            ListNode current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }

            //actually insertion
            newNode.next = current;
            newNode.prev = current.prev;
            current.prev.next = newNode;
            current.prev = newNode;
        }

        size++;

    }

    // Returns the value at location index.
    public T get(int index) {
        //checks for valid index
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        ListNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.value;
    }

    // Returns the first value in the list.
    public T getFirst() {
        // TODO
        return head.value;
    }

    // Returns the last value in the list.
    public T getLast() {
        // TODO
        return tail.value;
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
         if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        ListNode current = head;

        if (size == 1) {
            T value = head.value;
            head = null;
            tail = null;
            size--;
            return value;
        }

        if (index == 0) {
            T value = head.value;
            head = head.next;
            head.prev = null;
            size--;
            return value;
        }

        if (index == size - 1) {
            T value = tail.value;
            tail = tail.prev;
            tail.next = null;
            size--;
            return value;
        }

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        T value = current.value;
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;

        return value;

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