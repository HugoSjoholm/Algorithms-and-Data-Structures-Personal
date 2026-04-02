public class GenericSorting{

  public static <T extends Comparable<T>> void selectionSort(T[] a){
      for (int i = 0; i < a.length ; i++ ) {
        int minPosAfterI = minAfter(i, a);
        GenericArraySupport.exch(i, minPosAfterI, a);
      }
  }

  private static <T extends Comparable<T>> int minAfter(int pos, T[] a){
  // find the position of the minimum element after position pos
    int minPos = pos;
    for (int j = pos + 1; j < a.length ; j++ ) {
      if(less(a[j], a[minPos])) {
        minPos = j;
      }
    }
    return minPos;
  }

  public static <T extends Comparable<T>> void insertionSort(T[] a){
    for (int i = 1; i < a.length ; i++) {
      percolate(i, a);
    }
  }

  private static <T extends Comparable<T>>void percolate(int pos, T[] a){
    int j = pos;
    while(j > 0 && less(a[j], a[j-1])){
        GenericArraySupport.exch(j, j-1, a);
        j--;
    }
  }

  public static <T extends Comparable<T>> boolean less(T a, T b){
    return a.compareTo(b) < 0;
  }

  /*
  The purpose of main is to showcase the methods of this class.
  */
  public static void main(String[] args) {

    Integer[] numbers = {9,1,8,2,7,3,6,4,5};
    System.out.print("\nSelection sorting numbers: ");
    GenericArraySupport.println(numbers);
    selectionSort(numbers);
    System.out.print("results in: ");
    GenericArraySupport.println(numbers);

    Integer[] numbers1 = {9,1,8,2,7,3,6,4,5};
    System.out.print("\nInsertion sorting numbers: ");
    GenericArraySupport.println(numbers1);
    insertionSort(numbers1);
    System.out.print("results in: ");
    GenericArraySupport.println(numbers1);

    System.out.print("\nInsertion sorting strings: ");
    GenericArraySupport.println(args);
    insertionSort(args);
    System.out.print("results in: ");
    GenericArraySupport.println(args);
   }
}
