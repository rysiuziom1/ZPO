import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;

public class MySort {
    public static void sortStrings(Collator collator, String[] words) {
        int len = words.length;

        for (int i = 1; i < len; i++) {
            String toInsert = words[i];
            int j = i - 1;
            while (j >= 0 && collator.compare(words[j], toInsert) > 0) {
                words[j + 1] = words[j];
                j--;
            }
            words[j + 1] = toInsert;
        }
    }

    public static void fastSortStrings(Collator collator, String[] words) {
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return collator.compare(o1, o2);
            }
        };
        Arrays.sort(words, comparator);
    }

    public static void fastSortStrings2(Collator collator, String[] words) {
        Comparator<String> comparator = (s1, s2) -> collator.compare(s1, s2);
        Arrays.sort(words, comparator);
    }
}
