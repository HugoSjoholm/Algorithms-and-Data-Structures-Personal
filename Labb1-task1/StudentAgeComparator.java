// if necessary add imports

public class StudentAgeComparator {

 // add your code here and update the class
  public static String getPersonNumber(Student s) {
      String text = s.toString();

      int start = text.indexOf("pn: ") + 4;
      int end = text.indexOf(",", start);

      return text.substring(start, end);
  }
  public static long getEpochFromStudent(Student s) {
    String pn = getPersonNumber(s);   // example: 0101019999

    int yy = Integer.parseInt(pn.substring(0, 2));
    int mm = Integer.parseInt(pn.substring(2, 4));
    int dd = Integer.parseInt(pn.substring(4, 6));

    int year;
    if (yy <= 25) {
        year = 2000 + yy;
    } else {
        year = 1900 + yy;
    }

    java.time.LocalDate date = java.time.LocalDate.of(year, mm, dd);
    return date.toEpochDay() * 86400;
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

    Student[] fourSorted = {a, b, c, d};;
    Student swap = null;
    for (int i = 0; i < fourSorted.length; i++) {
      long tmpAge = getEpochFromStudent(fourSorted[i]);
      for (int j = 0; j < fourSorted.length - 1; j++) {
        if (tmpAge > getEpochFromStudent(fourSorted[j])) {
          swap = fourSorted[j];
          fourSorted[j] = fourSorted[j + 1];
          fourSorted[j + 1] = swap;
        }
      }
    }





System.out.println("Sorted array: ");
GenericArraySupport.printlns(fourSorted);
System.out.println();


    
  }
  // methods developed by the student
}