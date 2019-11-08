import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WordsCollection {
    private List<String> learnedWords;
    private List<String> actuallyForgottenWords;
    private static final Random rand = new Random();

    public WordsCollection() {
        learnedWords = new ArrayList<>();
        actuallyForgottenWords = new ArrayList<>();
    }

    public void learnWords(List<String> wordsDatabase) {
        int bound = wordsDatabase.size() - 1;
        String w1 = wordsDatabase.remove(rand.nextInt(bound));
        String w2 = wordsDatabase.remove(rand.nextInt(bound - 1));
        learnedWords.addAll(Arrays.asList(w1, w2));
    }

    public void forgetWords(List<String> wordDatabase, int k) {
        
    }

    public List<String> getLearnedWords() {
        return learnedWords;
    }

    public List<String> getActuallyForgottenWords() {
        return actuallyForgottenWords;
    }
}
