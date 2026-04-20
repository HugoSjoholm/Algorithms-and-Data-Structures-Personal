import java.util.Stack;

public class ReverseDLL {

    public static <T> void reverse(DoublyLinkedList<T> dll) {
        Stack<T> stack = new Stack<>();

        for (int i = 0; i < dll.size(); i++) {
            stack.push(dll.get(i));
        }

        dll.clear();

        while (!stack.isEmpty()) {
            dll.add(stack.pop());
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList<String> original = new DoublyLinkedList<>();
        DoublyLinkedList<String> test = new DoublyLinkedList<>();

        for (int i = 0; i < args.length; i++) {
            original.add(args[i]);
            test.add(args[i]);
        }

        reverse(test);
        reverse(test);

        boolean same = true;

        if (original.size() != test.size()) {
            same = false;
        } else {
            for (int i = 0; i < original.size(); i++) {
                String a = original.get(i);
                String b = test.get(i);

                if ((a == null && b != null) || (a != null && !a.equals(b))) {
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