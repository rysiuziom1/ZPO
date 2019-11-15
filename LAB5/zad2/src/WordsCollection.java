import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordsCollection {
    private List<String> learnedWords;
    private List<String> actuallyLearnedWords;
    private List<String> actuallyForgottenWords;
    private static final Random rand = new Random();
    private static final int learnedWordsPerDay = 2;

    public WordsCollection() {
        learnedWords = new ArrayList<>();
        actuallyLearnedWords = new ArrayList<>();
        actuallyForgottenWords = new ArrayList<>();
    }

    public void learnWords(List<String> wordsDatabase) {
        actuallyLearnedWords.clear();
        int bound = wordsDatabase.size();
        actuallyLearnedWords.add(wordsDatabase.remove(rand.nextInt(bound)));
        actuallyLearnedWords.add(wordsDatabase.remove(rand.nextInt(bound - 1)));
        learnedWords.addAll(actuallyLearnedWords);
    }

    public void forgetWords(List<String> wordDatabase, int days) {
        actuallyForgottenWords.clear();
        for(int i = 0; i < 2; i++) {
            int bound = learnedWords.size() - days * learnedWordsPerDay;
            if (bound > 0) {
                int toForgetIndex = rand.nextInt(bound);
                String toForget = learnedWords.get(toForgetIndex);
                if(rand.nextDouble() > 0.5d) {
                    actuallyForgottenWords.add(toForget);
                    learnedWords.remove(toForget);
                }
            }
        }
        wordDatabase.addAll(actuallyForgottenWords);
    }

    @Override
    public String toString() {
        return "New words: " + actuallyLearnedWords +
                "\nForgotten words: " + actuallyForgottenWords +
                "\n" + learnedWords + "\n";
    }
}
