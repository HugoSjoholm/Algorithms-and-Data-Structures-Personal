import java.lang.reflect.Array;
public class GenericArraySupport{
  /*
  Print all the elements on one line
  */
  public static <T> void println(T[] a){
    for(T x : a){
      System.out.print(x + " ");
    }
    System.out.println();
  }
  /*
  Print all the elements, one per line
  */
  public static <T> void printlns(T[] a){
    for(T x : a){
      System.out.println(x);
    }
  }

  /*
    Reverse in place
  */
  public static <T> void reverse(T[] a){
    T tmp;
    for (int  i = 0; i < a.length / 2 ; i++ ) {
      tmp = a[i];
      a[i] = a[a.length - 1 - i];
      a[a.length - 1 - i] = tmp;
    }
  }

  /*
  Exchange elements in positions p1 and p2 in the array a
  */
  public static <T> void exch(int p1, int p2, T[] a){
    T swap = a[p1];
    a[p1]  = a[p2];
    a[p2]  = swap;
   }

   /*
   Generate an array with times elements all of them with value v.
   Here to show how to create instances of an array of type T:
   You cannot use new T[times]!
   And (T[]) new Object[times] compiles but does not work!
   */
   public static <T> T[] repeat(T v, int times){
     T[] res = (T[]) Array.newInstance(v.getClass(), times);
     for (int i = 0; i < times ; i++) {
       res[i] = v;
     }
     return res;
   }

   /*
   Main demonstrates the methods defined in this class of
   help methods for generic arrays.
   */
   public static void main(String[] args) {
     System.out.println("\nWith String:");
     String[] words = {"this", "is", "a", "sentence", "."};
     System.out.println("Print one line: ");
     println(words);

     System.out.println("\nExchange positions 0 and 1, print many lines:");
     exch(0,1,words);
     printlns(words);

     System.out.println("\nReverse and print one line:");
     reverse(words);
     println(words);

     System.out.println("\nWith Integer:");
     Integer[] numbers = {1,2,3,4,5,6,7,8,9,10};
     System.out.println("Print one line: ");
     println(numbers);

     System.out.println("\nExchange positions 0 and 1, print many lines:");
     exch(0,1,numbers);
     printlns(numbers);

     System.out.println("\nReverse and print one line:");
     reverse(numbers);
     println(numbers);

     System.out.println("\nRepeat with doubles:");
     Double[] reals = repeat(2.0, 3);
     println(reals);
   }
 }
