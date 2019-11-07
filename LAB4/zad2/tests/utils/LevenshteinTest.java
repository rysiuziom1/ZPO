package utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class LevenshteinTest {

    @Test
    public void LevQWERTYTest() {
        assertEquals(0.0d, Levenshtein.LevQWERTY("ala", "ala"), 0);
        assertEquals(1.0d, Levenshtein.LevQWERTY("ala", "alma"), 0);
        assertEquals(2.0d, Levenshtein.LevQWERTY("ala", "almag"), 0);
        assertEquals(2.0d, Levenshtein.LevQWERTY("ala", "alkra"), 0);
        assertEquals(1.0d, Levenshtein.LevQWERTY("ala", "ale"), 0);
    }

}