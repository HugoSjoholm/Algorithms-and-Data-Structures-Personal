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
       @Override
        public String toString() {
            String output = "";
            if (prev == null) {
                output += "null";
            } else {
                output += String.valueOf(prev.value);
            }

            output += " <- " + value + " -> ";

            if (next == null) {
                output += "null";
            } else {
                output += String.valueOf(next.value);
            }

            //prev + " <- " + value + " -> " + next
            return output;
        }
    }

    private ListNode head;
    private ListNode tail;
    private int size;

    // Create an empty list.
    public DoublyLinkedList() {
        size = 0;
        head = null;
        tail = null;
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
        int count = 0;

        ListNode current = head;
        for (int i = 0; i < size; i++) {
            if (current.value == t) {
                remove(i);
                count ++;
                i--;
            }

            current = current.next;
        }

        return count;
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
        return size==0;
    }

    // Returns the size of the list.
    public int size() {
        // TODO
        return size;
    }

    // Removes all elements in the list. Makes it empty.
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // Add t after the first value smaller than t when scanning backwards.
    public void addAtFirstSmaller(T t, Comparator<T> cmp) {
        ListNode current = tail;

        while (current != null) {
            if (cmp.compare(current.value, t) < 0) {
                if (current == tail) {
                    add(t);
                } else {
                    ListNode newNode = new ListNode(t);
                    newNode.next = current.next;
                    newNode.prev = current;
                    current.next.prev = newNode;
                    current.next = newNode;
                    size++;
                }
                return;
            }
            current = current.prev;
        }

        // no smaller found → insert at front
        ListNode newNode = new ListNode(t);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    @Override
    public String toString() {
        String output = "{";

        ListNode current = head;
        if (current != null) {
            output += current.value;
            current = current.next;
        }
        else {
            output = "{null, null}";
            return output;
        }


        while (current != null) {
            output += ", " + current.value;
            current = current.next;
        }

        output += "}";
        return output;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {
            private ListNode current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }

                T value = current.value;
                current = current.next;
                return value;
            }
        };

        return it;
    }

    // Optional for checking ---
    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }

    //när skulle man använda dessa????????
    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }
    public static void main(String[] args) {

        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("Efter add: " + list);

        list.add(1, 99);
        System.out.println("Efter add på index 1: " + list);

        System.out.println("get(1): " + list.get(1));
        System.out.println("först: " + list.getFirst());
        System.out.println("sista: " + list.getLast());

        System.out.println("remove(1): " + list.remove(1));
        System.out.println("Efter remove index 1: " + list);

        list.add(2);
        list.add(2);
        System.out.println("pre remove(T): " + list);
        System.out.println("antal borttagna (2): " + list.remove((Integer)2));
        System.out.println("Efter remove(T): " + list);

        System.out.println("tom: " + list.isEmpty());

        System.out.print("går igenom: ");
        for (int x : list) {
            System.out.print(x + " ");
        }
        System.out.println();

        Comparator<Integer> cmp = Integer::compare;
        list.addAtFirstSmaller(4, cmp);
        System.out.println("efter addAtFirstSmaller(4): " + list);

        list.clear();
        System.out.println("efter clear: " + list + " | är tom: " + list.isEmpty());
    }
}