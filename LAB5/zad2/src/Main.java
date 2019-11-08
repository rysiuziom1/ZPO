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
        List<String> learnedWords = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            learnedWords.add(words.remove(rand.nextInt(words.size() - 1)));
            learnedWords.add(words.remove(rand.nextInt(words.size() - 1)));
            List<String> forgottenWords = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                int bound = learnedWords.size() - k * 2;
                if(bound > 0) {
                    int toForget = rand.nextInt(bound);
                    double probability = rand.nextDouble();
                    if (toForget >= 0 && probability >= 0.5d) {
                        String forgottenWord = learnedWords.remove(toForget);
                        words.add(forgottenWord);
                        forgottenWords.add(forgottenWord);
                    }
                }
            }
            System.out.println("Day " + (i + 1));
            System.out.println("New words: \t" + learnedWords.get(learnedWords.size() - 2) + " " + learnedWords.get(learnedWords.size() - 1));
            System.out.println("Forgotten words: " + forgottenWords);
            System.out.println(learnedWords);
        }
    }
}
