import java.util.Scanner;

public class WordCount {

    public static void main(String[] args) {
        BSTMap<String, Integer> words = new BSTMap<String, Integer>();
        Scanner input = new Scanner(System.in);

        while (input.hasNext()) {
            String word = input.next();

            word = word.toLowerCase();
            word = word.replaceAll("[^a-z]", "");

            if (word.length() > 0) {
                if (words.contains(word)) {
                    words.put(word, words.get(word) + 1);
                } else {
                    words.put(word, 1);
                }
            }
        }

        for (String word : words.keys()) {
            System.out.println(word + " " + words.get(word));
        }

        input.close();
    }
}