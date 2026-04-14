import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class SandC {

    public static Pair<Student, String> toPair(String line) {
        String[] parts = line.split(" ");

        String pn = parts[0];
        String lastName = parts[1];
        String firstName = parts[2];
        String code = parts[3];

        Student s = new Student(pn, firstName, lastName);

        return new Pair<Student, String>(s, code);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Pair<Student, String>[] arr = (Pair<Student, String>[]) new Pair[100];
        int size = 0;

        while (scan.hasNextLine()) {
            String line = scan.nextLine();

            if (!line.equals("")) {
                arr[size] = toPair(line);
                size++;
            }
        }

        Pair<Student, String>[] data = (Pair<Student, String>[]) new Pair[size];
        for (int i = 0; i < size; i++) {
            data[i] = arr[i];
        }

        if (args[0].equals("student")) {
            GenericSorting.insertionSort(data);
        } else if (args[0].equals("code")) {
            Arrays.sort(data, new Comparator<Pair<Student, String>>() {
                public int compare(Pair<Student, String> p1, Pair<Student, String> p2) {
                    return p1.snd.compareTo(p2.snd);
                }
            });
        }

        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
    }
}