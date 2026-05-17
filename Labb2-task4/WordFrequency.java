import java.util.Comparator;
import java.util.Scanner;

public class WordFrequency {
    public static void main(String[] args) {
        LinearProbingHashMap<String, Integer> map = new LinearProbingHashMap<String, Integer>();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String word = scanner.next();

            if (map.contains(word)) {
                int count = map.get(word);
                map.put(word, count + 1);
            } else {
                map.put(word, 1);
            }
        }

        Comparator<Integer> comparator = new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return a.compareTo(b);
            }
        };

        for (String word : map.keys(comparator)) {
            System.out.println(word + " " + map.get(word));
        }

        scanner.close();
    }
}
