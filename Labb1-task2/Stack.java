import java.util.Iterator;

public class Stack<T> {
  private class Node{
    T item;
    Node next;
  }
  private Node first;
  private int size;

  public Stack(){
    first = null;
    size  = 0;
  }

  public void push(T item){
    Node topNode = new Node();
    topNode.item = item;
    topNode.next = first;
    first = topNode;
    size++;
  }


  public T pop(){
    T top = first.item;
    first = first.next;
    size--;
    return top;
  }


  public boolean isEmpty(){
    return first == null;
  }

  public int size(){
    return size;
  }

  //------------------------------------------------------------
  // Stack of Strings test ----LIFO-(Last in - Last Out)--------
  //------------------------------------------------------------
  public static void main(String[] args) {
    
	String args1[] = {"Peter","Sven","Mary","Cris","Alex"};  // comment this line if you are using the "args" array in command line.
	
	Stack<String> words = new Stack<String>();
    
    for (String word : args1) { // change args1 to args if you are using "args" in command line.
      words.push(word);
    }

    while(!words.isEmpty()){
      System.out.print(words.pop() + " ");
    }
    System.out.println();
  }
}
