import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    final static private int n = 10, k = 3;

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        URL namesURL = null;
        try {
            namesURL = new URL("http://szgrabowski.iis.p.lodz.pl/zpo19/1500.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(namesURL.openStream()));
            String word;
            while ((word = br.readLine()) != null) {
                words.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        WordsCollection wordsCollection = new WordsCollection();
        for(int i = 0; i < n; i++) {
            wordsCollection.learnWords(words);
            wordsCollection.forgetWords(words, k);
            System.out.println("Day " + (i + 1));
            System.out.println(wordsCollection);
        }
    }
}
