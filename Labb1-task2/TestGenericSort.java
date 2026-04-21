import java.util.Arrays;
import java.util.function.Consumer;

public class TestGenericSort{

  public static <T extends Comparable<T>> boolean isSorted(T[] a){
    for (int i = 1; i < a.length ; i++ ) {
      if(GenericSorting.less(a[i],a[i-1])){
        return false;
      }
    }
    return true;
  }

  public static Integer[] randomInts(int size, int lb, int ub){
    Integer[] ran = new Integer[size];
    for (int i = 0; i < size; i++) {
      ran[i] = lb + (int)(Math.random()*(ub - lb + 1));
    }
    return ran;
  }

  /*
  This method can test selection sort on arrays of Integer:
  */
  public static void testSelectionSort(){
    Integer[] a;
    for(int size = 0; size < 1000; size++){
      a = randomInts(size, -10*size, 10*size);
      GenericSorting.selectionSort(a);
      if(!isSorted(a)){
        System.out.println("Failed test for size " + size);
        return;
      }
    }
    System.out.println("Passed tests for sizes 0 ... 1000");
  }

  /*
  This method can test any sorting algorithm on arrays of Integer.
  Consumer<T[]> is the type of any method with signature
       void name(T[] a)
  So, for example, all sorting methods in Sorting have this type!
  We can use
      testSortingAlg(Sorting::selectionSort)
  and we can use
      testSortingAlg(Sorting::insertionSort)
  */
  public static void testSortingAlg(Consumer<Integer[]> xSort){
    Integer[] a;
    for(int size = 0; size < 1000; size++){
      a = randomInts(size, -10*size, 10*size);
      xSort.accept(a);
      if(!isSorted(a)){
          System.out.println("Failed test for size " + size);
          return;
      }
    }
    System.out.println("Passed tests for sizes 0 ... 1000");
  }


  /*
  This method can measure running time of any sorting algorithm
  on any array.
  Consumer<T[]> is the type of any method with signature
       void name(T[] a)
  So, for example, all sorting methods in Sorting have this type!
  We can use
      measureTime(Sorting::selectionSort, a)
  and we can use
      measureTime(Sorting::insertionSort, a)
  */
  public static <T> double measureTime(Consumer<T[]> f, T[] a){
    long start = System.currentTimeMillis();
    f.accept(a);
    long stop = System.currentTimeMillis();
    return (stop - start)/1000.0;
  }

  /*
    This method measures running time of any sorting algorithm
    on arrays of Integer for a given size.
  */
  public static double measureTimeInts(Consumer<Integer[]> f, int size){
    Integer[] a;
    double sum = 0;
    for (int i = 0; i < 10 ; i++ ) {
      a = randomInts(size, -size, size);
      long start = System.currentTimeMillis();
      f.accept(a);
      long stop = System.currentTimeMillis();
      sum = sum + (stop - start)/1000.0;
    }
    return sum / 10;
  }

 /*
 The method main illustrates the use of the methods defined in this class
 */
  public static void main(String[] args) {

    System.out.println("\nUsing testSelectionSort:");
    testSelectionSort();
    
    System.out.println("\nUsing testSortingAlg with insertion sort:");
    testSortingAlg(GenericSorting::insertionSort);

    Double[] reals = {.1, -.2, .38, .009};
    System.out.println("\nMeasuring time of sorting with selection sort a small array");
    System.out.println(measureTime(GenericSorting::selectionSort, reals));

    System.out.println("\nMeasuring time of sorting with selection sort");
    System.out.println("avarage of 10 random arrays of Integers of size 10000");
    System.out.println(measureTimeInts(GenericSorting::selectionSort, 10000));
  }
}
