import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LevenshteinTest {

    @Test
    public void levQWERTYTestAllRight() {
        assertEquals(1.5, Levenshtein.LevQWERTY("kot", "kita"), 0.0);
        assertEquals(0.5, Levenshtein.LevQWERTY("kwiat", "kwist"), 0.0);
        assertEquals(2.0, Levenshtein.LevQWERTY("drab", "dal"), 0.0);
        assertEquals(2.5, Levenshtein.LevQWERTY("lama", "aakama"), 0.0);
    }

    @Test
    public void levQWERTYTestAllWrong() {
        assertNotEquals(1.5, Levenshtein.LevQWERTY("kot", "kit"), 0.0);
        assertNotEquals(0.0, Levenshtein.LevQWERTY("kot", "karta"), 0.0);
        assertNotEquals(2, Levenshtein.LevQWERTY("kot", "kot"), 0.0);
        assertNotEquals(1.0, Levenshtein.LevQWERTY("kamien", "koniec"), 0.0);
    }
}