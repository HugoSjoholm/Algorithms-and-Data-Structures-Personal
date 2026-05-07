import java.util.NoSuchElementException;

public class MaxPQ<T extends Comparable<T>> {

    private T[] pq;
    private int n;

    public MaxPQ() {
        pq = (T[]) new Comparable[2];
        n = 0;
    }

    public MaxPQ(T[] a) {
        n = a.length;
        pq = (T[]) new Comparable[n + 1];

        for (int i = 0; i < n; i++) {
            pq[i + 1] = a[i];
        }

        for (int k = n / 2; k >= 1; k--) {
            sink(k);
        }
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(T item) {
        if (n == pq.length - 1) {
            resize(2 * pq.length);
        }

        pq[++n] = item;
        swim(n);
    }

    public T delMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }

        T max = pq[1];

        T temp = pq[1];
        pq[1] = pq[n];
        pq[n] = temp;

        n--;
        sink(1);

        pq[n + 1] = null;

        if (n > 0 && n == (pq.length - 1) / 4) {
            resize(pq.length / 2);
        }

        return max;
    }

    private void swim(int k) {
        while (k > 1 && pq[k / 2].compareTo(pq[k]) < 0) {
            T temp = pq[k];
            pq[k] = pq[k / 2];
            pq[k / 2] = temp;

            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;

            if (j < n && pq[j].compareTo(pq[j + 1]) < 0) {
                j++;
            }

            if (pq[k].compareTo(pq[j]) >= 0) {
                break;
            }

            T temp = pq[k];
            pq[k] = pq[j];
            pq[j] = temp;

            k = j;
        }
    }

    private void resize(int capacity) {
        T[] copy = (T[]) new Comparable[capacity];

        for (int i = 1; i <= n; i++) {
            copy[i] = pq[i];
        }

        pq = copy;
    }
}