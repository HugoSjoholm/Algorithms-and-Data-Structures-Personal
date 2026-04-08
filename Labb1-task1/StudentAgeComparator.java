// if necessary add imports

public class StudentAgeComparator {

 // add your code here and update the class
  public static int cleaString(Student s) {
    int year = Integer.parseInt(s.personNumber.substring(0, 2));
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

    
  }
  // methods developed by the student
}
