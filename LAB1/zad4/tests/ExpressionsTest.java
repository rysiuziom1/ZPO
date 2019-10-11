import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionsTest {

    @Test
    public void algebraicToWordExpressionTest() {
        assertEquals("pięć minus dwa razy trzy ", Expressions.algebraicToWordExpression("5 - 2 * 3"));
        assertEquals("pięć minus minus dwa razy trzy ", Expressions.algebraicToWordExpression("5 - -2 * 3"));
        assertEquals("pięć plus minus dwa razy trzy ", Expressions.algebraicToWordExpression("5 + -2 * 3"));
        assertEquals("dwadzieścia pięć plus minus dziesięć razy zero ", Expressions.algebraicToWordExpression("25 + -10 * 0"));
        assertEquals("dwadzieścia pięć plus minus dziesięć razy zero ", Expressions.algebraicToWordExpression("25 + -10 * 00"));
        assertEquals("dwadzieścia pięć plus minus jeden razy zero ", Expressions.algebraicToWordExpression("25 + -01 * 00"));
    }
}