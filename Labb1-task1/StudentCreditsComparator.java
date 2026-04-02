import java.util.Comparator;
import java.util.Arrays;

public class StudentCreditsComparator implements Comparator<Student>{

  public int compare(Student s1, Student s2){
    /*
      Replace return 0; with your code.
    */
    return 0;
  }

  public static void main(String[] args) {
    Student a = new Student("0101019999", "Student", "Randomsdotter");
    Student b = new Student("0202028888", "She", "Studentdotter");
    Student c = new Student("0302017777", "He", "Studentsson");
    Student d = new Student("0302016666", "Student", "Randomsson");
    a.addCredits(7.5);
    b.addCredits(15);
    c.addCredits(4.5);
    d.addCredits(3);
    Student[] four = {a, b, c, d};
    System.out.println("Array with students: ");
    GenericArraySupport.printlns(four);
    System.out.println();

    Arrays.sort(four, new StudentCreditsComparator());
    System.out.println("Sorted array: ");
    GenericArraySupport.printlns(four);
    System.out.println();
   }
}
