
public class Pair<A extends Comparable<A>,B extends Comparable<B>>
             implements Comparable<Pair<A,B>> {

  public A fst;
  public B snd;

  public Pair(A a, B b){
    fst = a;
    snd = b;
  }
  public String toString(){
    return "(" + fst + ", " + snd + ")";
  }

  public boolean equals(Pair<A,B> that){
    return this.fst.equals(that.fst)
              &&
           this.snd.equals(that.snd);
  }

  public int compareTo(Pair<A,B> that){
    if (this.fst.compareTo(that.fst) < 0) return -1;
    if (this.fst.compareTo(that.fst) > 0) return 1;
    // equal first component:
    if (this.snd.compareTo(that.snd) < 0) return -1;
    if (this.snd.compareTo(that.snd) > 0) return 1;
    // also equal second component:
    return 0;
 }

  public static void main(String[] args) {
    Pair<String, Integer> address1 = new Pair<String,Integer>("Nygatan", 34);
    Pair<String, Integer> address2 = new Pair<String,Integer>("Skolgatan", 34);
    System.out.println(address1);
    System.out.println(address2);
    System.out.println(address1.equals(address2));
    System.out.println(address1.compareTo(address2));
  }
}
