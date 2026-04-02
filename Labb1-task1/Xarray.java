public class Xarray<T>{

  public Xarray(){}

  public void add(T x){}

  public T lookup(int i){return null;}


  public void update(int i, T x){}


  public int size(){return 0;}
  /*
    Small unit test
  */
  public static void main(String[] args){

    Xarray<Integer> a = new Xarray<Integer>();
    for(int i = 0; i < 30; i++){
      a.add(i);
    }

    a.update(10,-1);

    for(int i = 0; i < 30; i++){
      System.out.print(a.lookup(i) + " ");
    }
    System.out.println();

    Integer[] arr = new Integer[a.size()];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = a.lookup(i);
    }
    GenericSorting.insertionSort(arr);
    GenericArraySupport.println(arr);

    a.update(60,10);

  }
}
