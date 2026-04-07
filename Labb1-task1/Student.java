import java.util.Arrays;

import javax.print.FlavorException;
public class Student implements Comparable<Student>{

  private final String personNumber;
  private final String firstName;
  private final String lastName;
  private double credits;

  public Student(String pn, String fn, String ln){
    personNumber = pn;
    firstName    = fn;
    lastName     = ln;
    credits      = 0;
  }

  public String toString(){
    return "[Student]/"
          + lastName + ", " + firstName + ", pn: " + personNumber
          + ", credits: " + credits + "/";
  }

  public boolean equals(Student that){
    
    if (this.firstName != that.firstName) {
      return false;
    }
    if (this.lastName != that.lastName) {
      return false;
    }
    if (this.personNumber != that.personNumber) {
      return false;
    }
    return true;
    
    /*
    Replace return false; with your code.
    */
  }

  public int compareTo(Student that){
    //runs the .equals to avoid a dussin more checks.
    if (this.equals(that)) {
      return 0;
    }
    
    //last name
    if (this.lastName.compareTo(that.lastName) > 0) {
      return 1;
    }
    if (this.lastName.compareTo(that.lastName) < 0) {
      return -1;
    }

    //first name
    if (this.lastName.compareTo(that.lastName) > 0) {
      return 1;
    }
    if (this.lastName.compareTo(that.lastName) < 0) {
      return -1;
    }

    //personnummer
    //assuming the personal number 990101 is greater than 030101
   /*
   
   */
    if (Integer.parseInt( this.personNumber) > Integer.parseInt(that.personNumber)) {
      return -1;
    }
    if (Integer.parseInt( this.personNumber) < Integer.parseInt(that.personNumber)) {
      return 1;
    }
   
    //Error case
    return Integer.MAX_VALUE;
    /*
    Replace return 0; with your code.
    */
  }

  public void addCredits(double c){
    this.credits += c;
    /*
    Your code here.
    */
  }

  public double credits(){
    /*
    Replace return 0; with your code.
    */
   return credits;
  }

  public static void main(String[] args) {
    Student a = new Student("0101019999", "Student", "Randomsdotter");
    Student b = new Student("0202028888", "She", "Studentdotter");
    Student c = new Student("0302017777", "He", "Studentsson");
    Student d = new Student("0302016666", "Student", "Randomsson");

    System.out.println(a + " equals " + a + " is " + a.equals(a));
    System.out.println();

    System.out.println(b + " equals " + c + " is " + b.equals(c));
    System.out.println();

    Student[] four = {a, b, c, d};
    System.out.println("Array with students: ");
    GenericArraySupport.printlns(four);
    System.out.println();

    GenericSorting.insertionSort(four);
    System.out.println("Sorted array: ");
    GenericArraySupport.printlns(four);
    System.out.println();

    a.addCredits(7.5);
    b.addCredits(15);
    c.addCredits(4.5);
    d.addCredits(3);
    System.out.println("Added credits: ");
    GenericArraySupport.printlns(four);
  }
}
