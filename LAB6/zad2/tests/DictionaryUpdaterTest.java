import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class DictionaryUpdaterTest {
    private Map<String, Integer> dictionary;
    private String word = "dog";

    @Before
    public void makeDictionary() {
        dictionary = new HashMap<>();
        dictionary.merge(word, 1, Integer::sum);
    }

    @Test
    public void updateByContainsKeyTest() {
        DictionaryUpdater.updateByContainsKey(dictionary, word);
        assertEquals(2, dictionary.get(word).intValue());
    }

    @Test
    public void updateByGetTest() {
        DictionaryUpdater.updateByGet(dictionary, word);
        assertEquals(2, dictionary.get(word).intValue());
    }

    @Test
    public void updateByGetOrDefaultTest() {
        DictionaryUpdater.updateByGetOrDefault(dictionary, word);
        assertEquals(2, dictionary.get(word).intValue());
    }

    @Test
    public void updateByPutIfAbsentTest() {
        DictionaryUpdater.updateByPutIfAbsent(dictionary, word);
        assertEquals(2, dictionary.get(word).intValue());
    }
}