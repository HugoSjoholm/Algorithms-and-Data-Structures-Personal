import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


public class LinearProbingHashMap<T1, T2> {

    private static final int INIT_CAPACITY = 4;

    private int N;          
    private int M;          
    private T1[] a_keys;    
    private T2[] a_values;  

    public LinearProbingHashMap() {
        this(INIT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public LinearProbingHashMap(int M) {
        this.M = M;
        this.N = 0;
        a_keys = (T1[]) new Object[M];
        a_values = (T2[]) new Object[M];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private int hash(T1 key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public boolean contains(T1 key) {
        return get(key) != null;
    }

    public T2 get(T1 key) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        for (int i = hash(key); a_keys[i] != null; i = (i + 1) % M) {
            if (a_keys[i].equals(key)) {
                return a_values[i];
            }
        }

        return null;
    }

    public void put(T1 key, T2 val) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        if (val == null) {
            delete(key);
            return;
        }

        if (N >= M / 2) {
            resize(2 * M);
        }

        int i;
        for (i = hash(key); a_keys[i] != null; i = (i + 1) % M) {
            if (a_keys[i].equals(key)) {
                a_values[i] = val;
                return;
            }
        }

        a_keys[i] = key;
        a_values[i] = val;
        N++;
    }

    public void delete(T1 key) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        if (!contains(key)) {
            return;
        }

        int i = hash(key);

        while (!key.equals(a_keys[i])) {
            i = (i + 1) % M;
        }

        a_keys[i] = null;
        a_values[i] = null;

        i = (i + 1) % M;

        while (a_keys[i] != null) {
            T1 keyToRedo = a_keys[i];
            T2 valToRedo = a_values[i];

            a_keys[i] = null;
            a_values[i] = null;
            N--;

            put(keyToRedo, valToRedo);

            i = (i + 1) % M;
        }

        N--;

        if (N > 0 && N <= M / 8 && M > INIT_CAPACITY) {
            resize(M / 2);
        }
    }

    private void resize(int capacity) {
        LinearProbingHashMap<T1, T2> temp = new LinearProbingHashMap<T1, T2>(capacity);

        for (int i = 0; i < M; i++) {
            if (a_keys[i] != null) {
                temp.put(a_keys[i], a_values[i]);
            }
        }

        this.M = temp.M;
        this.N = temp.N;
        this.a_keys = temp.a_keys;
        this.a_values = temp.a_values;
    }

    public Iterable<T1> keys() {
        ArrayList<T1> list = new ArrayList<T1>();

        for (int i = 0; i < M; i++) {
            if (a_keys[i] != null) {
                list.add(a_keys[i]);
            }
        }

        return list;
    }

    public Iterable<T1> keys(Comparator<T2> cmp) {
        PriorityQueue<Entry<T1, T2>> pq =
            new PriorityQueue<Entry<T1, T2>>(new Comparator<Entry<T1, T2>>() {
                public int compare(Entry<T1, T2> e1, Entry<T1, T2> e2) {
                    return -cmp.compare(e1.value, e2.value);
                }
            });

        for (int i = 0; i < M; i++) {
            if (a_keys[i] != null) {
                pq.add(new Entry<T1, T2>(a_keys[i], a_values[i]));
            }
        }

        ArrayList<T1> result = new ArrayList<T1>();

        while (!pq.isEmpty()) {
            result.add(pq.remove().key);
        }

        return result;
    }

    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
