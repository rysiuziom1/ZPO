import org.junit.Test;


import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class NumberFormatterTest {

    @Test
    public void formattedNumbersTest() {
        List<Double> li = Arrays.asList((double)-5100, 43.257, (double)200000, 2000000.5);
        List<String> fn = NumberFormatter.formattedNumbers(li, 2, ',', 2, true);
        assertEquals(4, fn.size());
        assertEquals("    -51,00.00", fn.get(0));
        assertEquals("        43.26", fn.get(1));
        assertEquals("  20,00,00.00", fn.get(2));
        assertEquals("2,00,00,00.50", fn.get(3));

        List<String> fn2 = NumberFormatter.formattedNumbers(li, 3, '|', 2, false);
        assertEquals(4, fn2.size());
        assertEquals("   -5|100", fn2.get(0));
        assertEquals("       43.26", fn2.get(1));
        assertEquals("  200|000", fn2.get(2));
        assertEquals("2|000|000.5", fn2.get(3));
    }
}