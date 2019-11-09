import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class DateExercisesTest {

    @Test
    public void daysBetweenTest() {
        assertEquals(2, DateExercises.daysBetween(LocalDate.now(), LocalDate.now().plusDays(2)));
        assertEquals(21, DateExercises.daysBetween(LocalDate.now(), LocalDate.now().plusDays(21)));
        assertEquals(6, DateExercises.daysBetween(LocalDate.now(), LocalDate.now().plusDays(6)));
        assertNotEquals(2, DateExercises.daysBetween(LocalDate.now(), LocalDate.now().plusDays(3)));
    }

    @Test
    public void ofYearDayTest() {
        assertEquals(LocalDate.of(2019, 1, 24), DateExercises.ofYearDay(2019, 24));
        assertEquals(LocalDate.of(2019, 2, 2), DateExercises.ofYearDay(2019, 33));
        assertEquals(LocalDate.of(2016, 3, 1), DateExercises.ofYearDay(2016, 61));
    }

    @Test
    public void specificDigitsSumBetweenHoursCounterTest() {
        assertEquals(1, DateExercises.specificDigitsSumBetweenHoursCounter(
                LocalTime.of(9, 45), LocalTime.of(9, 47), 19
        ));

        assertEquals(2, DateExercises.specificDigitsSumBetweenHoursCounter(
                LocalTime.of(10, 12), LocalTime.of(10, 21), 4
        ));
    }

    @Test
    public void howMany29FebInLifeTest() {
        assertEquals(5, DateExercises.howMany29FebInLife(
                LocalDate.of(1997, 4, 24), LocalDate.of(2019, 11, 9)
        ));

        assertEquals(1, DateExercises.howMany29FebInLife(
                LocalDate.of(2016, 1, 1), LocalDate.of(2019, 11, 9)
        ));

        assertEquals(2, DateExercises.howMany29FebInLife(
                LocalDate.of(2012, 1, 1), LocalDate.of(2019, 11, 9)
        ));

        assertEquals(1, DateExercises.howMany29FebInLife(
                LocalDate.of(2012, 3, 1), LocalDate.of(2019, 11, 9)
        ));
    }
}