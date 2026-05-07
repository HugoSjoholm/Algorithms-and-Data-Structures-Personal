import java.util.Iterator;
import java.util.NoSuchElementException;

public class DataBuffer<T> implements Iterable<T> {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    private T[] buffer;
    private int front;
    private int back;
    private int size;

    public DataBuffer(int bufferSize) {
        if (bufferSize < 1) {
            throw new IllegalArgumentException("Buffer size must be at least 1");
        }

        buffer = (T[]) new Object[bufferSize];
        front = 0;
        back = 0;
        size = 0;
    }
    public synchronized void enqueue(T t) {
        if (isFull()) {
            throw new RuntimeException("Buffer is full");
        }

        buffer[back] = t;
        back = (back + 1) % buffer.length;
        size++;
    }

    public synchronized T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Buffer is empty");
        }

        T item = buffer[front];
        buffer[front] = null;
        front = (front + 1) % buffer.length;
        size--;

        return item;
    }

    public synchronized boolean isEmpty() {
        return size == 0;
    }

    public synchronized boolean isFull() {
        return size == buffer.length;
    }

    public synchronized int size() {
        return size;
    }
    public int bufferSize() {
        return buffer.length;
    }
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int count = 0;

            public boolean hasNext() {
                return count < size;
            }

            public T next() {
                if (!hasNext()) {
                    throw new RuntimeException("No more elements");
                }

                T item = (T)(buffer[(front + count) % buffer.length]);
                count++;
                return item;
            }
        };
    }
}