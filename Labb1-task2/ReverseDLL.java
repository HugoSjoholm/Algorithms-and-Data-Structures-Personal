import java.util.Stack;

public class ReverseDLL {

    public static <T> void reverse(DoublyLinkedList<T> dll) {
        // TODO
    }

    public static void main(String[] args) {
        DoublyLinkedList<String> original = new DoublyLinkedList<>();
        DoublyLinkedList<String> test = new DoublyLinkedList<>();

        for (String s : args) {
            original.add(s);
            test.add(s);
        }

        reverse(test);
        reverse(test);

        boolean same = true;
        if (original.size() != test.size()) {
            same = false;
        } else {
            for (int i = 0; i < original.size(); i++) {
                if (!original.get(i).equals(test.get(i))) {
                    same = false;
                    break;
                }
            }
        }

        if (same) {
            System.out.println("Reverse worked for the list " + original);
        } else {
            System.out.println("Reverse did not work for the list " + original + " and got " + test);
        }
    }
}