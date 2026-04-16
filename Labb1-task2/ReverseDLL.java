import java.util.Stack;

public class ReverseDLL {

    public static <T> void reverse(DoublyLinkedList<T> dll) {
        // TODO
    }

    public static void main(String[] args) {
        DoublyLinkedList<String> original = new DoublyLinkedList<>();
        DoublyLinkedList<String> test = new DoublyLinkedList<>();

        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            int rnd = (int)(Math.random()*100 + 65); 
            original.add((rnd) + "");
            test.add((rnd) + "");
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