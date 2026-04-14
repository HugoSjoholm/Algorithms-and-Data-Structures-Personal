import java.util.Scanner;
import java.util.Arrays;

public class StudentXarray {

    public static Student toStudent(String s) {
        String[] parts = s.split(" ");

        Student tmp = new Student(parts[0], parts[2], parts[1]);
        tmp.addCredits(Double.parseDouble(parts[3]));

        return tmp;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Xarray<Student> students = new Xarray<Student>();

        while (scan.hasNextLine()) {
            String line = scan.nextLine();

            if (!line.equals("")) {
                students.add(toStudent(line));
            }
        }

        scan.close();

        Student[] arr = new Student[students.size()];

        for (int i = 0; i < students.size(); i++) {
            arr[i] = students.lookup(i);
        }

        Arrays.sort(arr, new StudentCreditsComparator());

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}