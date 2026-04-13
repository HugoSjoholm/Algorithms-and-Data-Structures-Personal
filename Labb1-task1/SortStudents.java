import java.sql.Struct;
import java.util.Scanner;


public class SortStudents {
    public static Student toStudent(String s) {
        String[] parts = s.split(" ");
        //System.out.println(Double.parseDouble(parts[3]));
        Student tmp = new Student(parts[0], parts[2], parts[1]);
        tmp.addCredits(Double.parseDouble(parts[3]));
        //System.out.println(tmp);
        return tmp;
    }
    

    public static void sortByName(Student[] arr) {
        Student swap = null;
        
        for (int i = 0; i < arr.length; i++) {    
            for (int j = 0; j < arr.length - 1; j++) {
                if (getName(arr[j]).compareTo(getName(arr[j + 1])) > 0) {
                    //System.out.println("true");
                    swap = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = swap;
                }
            }
        }
    }
    public static void sortByAge(Student[] arr) {
        Student swap = null;
        
        for (int i = 0; i < arr.length; i++) {    
            for (int j = 0; j < arr.length - 1; j++) {
                if (StudentAgeComparator.getEpochAge(arr[j]) < StudentAgeComparator.getEpochAge(arr[j + 1])) {
                    //System.out.println("true");
                    swap = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = swap;
                }
            }
        }
    }
    public static void sortByCredit(Student[] arr) {
        Student swap = null;
        
        for (int i = 0; i < arr.length; i++) {    
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j].credits() > arr[j + 1].credits()) {
                    //System.out.println("true");
                    swap = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = swap;
                }
            }
        }
    }

    public static String getName(Student s) {
        String text = s.toString();

        int start = text.indexOf("/") + 1;
        int end = text.indexOf(", pn:");

        return text.substring(start, end);
    }
    public static void main(String[] args) {
        int ammountOfStudents = Integer.parseInt(args[0]);
        String sortingMethod = args[1];

        Scanner scanner = new Scanner(System.in);
        Student[] students = new Student[ammountOfStudents];

        for (int i = 0; i < students.length && scanner.hasNextLine(); i++ ) {
            String tmp = scanner.nextLine();
            //System.out.println("tmp: " + tmp);
            students[i] = toStudent(tmp);
            //System.out.println(students[i]);
        }
        scanner.close();


        //GenericArraySupport.printlns(students);
        //System.out.println("");


        //System.out.println(inputData);
        switch (sortingMethod) {
            case "name":
                //System.out.println(sortingMethod);
                
                //continue work here
                sortByName(students);
                GenericArraySupport.printlns(students);
            break;
            case "age":
                //System.out.println(sortingMethod);

                sortByAge(students);
                GenericArraySupport.printlns(students);
            break;

            case "credits":
                //System.out.println(sortingMethod);

                sortByCredit(students);
                GenericArraySupport.printlns(students);
            break;
            default: 
                System.out.println("Error wrong sorting method");
            break;
        }

    }
}