import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCoding {

    private Node root;
    private Map<Character, String> codes;
    private static final char END = '\0';

    private static class Node implements Comparable<Node> {
        char ch;
        int weight;
        Node left;
        Node right;

        Node(char ch, int weight) {
            this.ch = ch;
            this.weight = weight;
        }

        Node(Node left, Node right) {
            this.ch = '\0';
            this.weight = left.weight + right.weight;
            this.left = left;
            this.right = right;
        }

        boolean isLeaf() {
            return left == null && right == null;
        }

        public int compareTo(Node that) {
            return this.weight - that.weight;
        }
    }

    public HuffmanCoding(String text) {
        codes = new HashMap<Character, String>();
        root = buildTree(text);
        buildCodes(root, "");
    }

    private Node buildTree(String text) {
        Map<Character, Integer> count = new HashMap<Character, Integer>();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (count.containsKey(c)) {
                count.put(c, count.get(c) + 1);
            } else {
                count.put(c, 1);
            }
        }

        count.put(END, 1);

        PriorityQueue<Node> pq = new PriorityQueue<Node>();

        for (Character c : count.keySet()) {
            pq.add(new Node(c, count.get(c)));
        }

        while (pq.size() > 1) {
            Node first = pq.remove();
            Node second = pq.remove();
            pq.add(new Node(first, second));
        }

        return pq.remove();
    }

    private void buildCodes(Node node, String code) {
        if (node.isLeaf()) {
            codes.put(node.ch, code);
            return;
        }

        buildCodes(node.left, code + "0");
        buildCodes(node.right, code + "1");
    }

    public String encode(String text) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            result += codes.get(text.charAt(i));
        }

        result += codes.get(END);

        return result;
    }

    public String decode(String encodedText) {
        String result = "";
        Node current = root;

        for (int i = 0; i < encodedText.length(); i++) {
            char bit = encodedText.charAt(i);

            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }

            if (current.isLeaf()) {
                if (current.ch == END) {
                    return result;
                }

                result += current.ch;
                current = root;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String text = "this is a test for the lab HH";

        HuffmanCoding huffman = new HuffmanCoding(text);

        String encoded = huffman.encode(text);
        String decoded = huffman.decode(encoded);

        System.out.println("Original:");
        System.out.println(text);

        System.out.println("\nEncoded:");
        System.out.println(encoded);

        System.out.println("\nDecoded:");
        System.out.println(decoded);
    }
}