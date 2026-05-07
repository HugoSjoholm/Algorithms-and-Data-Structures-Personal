import java.util.ArrayList;

public class BSTMap<T1 extends Comparable<T1>, T2> {

    private Node root;
    private int size;

    private class Node {
        T1 key;
        T2 value;
        Node left;
        Node right;

        Node(T1 key, T2 value) {
            this.key = key;
            this.value = value;
        }
    }

    public BSTMap() {
        root = null;
        size = 0;
    }

    public void put(T1 key, T2 val) {
        root = put(root, key, val);
    }

    private Node put(Node x, T1 key, T2 val) {
        if (x == null) {
            size++;
            return new Node(key, val);
        }

        int cmp = key.compareTo(x.key);

        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.value = val;
        }

        return x;
    }

    public T2 get(T1 key) {
        Node x = root;

        while (x != null) {
            int cmp = key.compareTo(x.key);

            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x.value;
            }
        }

        return null;
    }

    public boolean contains(T1 key) {
        return get(key) != null;
    }

    public void delete(T1 key) {
        if (contains(key)) {
            root = delete(root, key);
            size--;
        }
    }

    private Node delete(Node x, T1 key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);

        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.right == null) {
                return x.left;
            }

            if (x.left == null) {
                return x.right;
            }

            Node temp = x;
            x = min(temp.right);
            x.right = deleteMin(temp.right);
            x.left = temp.left;
        }

        return x;
    }

    private Node min(Node x) {
        while (x.left != null) {
            x = x.left;
        }

        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }

        x.left = deleteMin(x.left);
        return x;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Iterable<T1> keys() {
        ArrayList<T1> list = new ArrayList<T1>();
        inorder(root, list);
        return list;
    }

    private void inorder(Node x, ArrayList<T1> list) {
        if (x == null) {
            return;
        }

        inorder(x.left, list);
        list.add(x.key);
        inorder(x.right, list);
    }
}