public class Xarray<T>{

  private T[] data;
  private int capacity;
  private int size;


  public Xarray(){
    capacity = 10;
    size = 0;
    data = (T[]) new Object[capacity];
  }

  public void add(T x){
    if (size < capacity) {
      data[size] = x;
      size++;
    }
    else {
      T[] tmp = (T[]) new Object[capacity*2];
      capacity = capacity*2;
      for (int i = 0; i < size; i++) {
        tmp[i] = data[i];
      }
      tmp[size] = x;
      size++;

      data = tmp;
    }
    
  }

  public T lookup(int i){
    if (i >= size || i < 0) { //i can not be negative or more then the current lenght   (seize)
      throw new IndexOutOfBoundsException()
    }
    return data[i];
  }


  public void update(int i, T x){
    if (i >= size || i < 0) { //i can not be negative or more then the current lenght   (seize)
      throw new IndexOutOfBoundsException()
    }
    data[i] = x;
  }


  public int size(){
    return size;
  }
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
