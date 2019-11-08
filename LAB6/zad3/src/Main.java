import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        // a)
        System.out.println(DateExercises.daysBetween(LocalDate.parse("1939-09-01"), LocalDate.parse("1945-05-08")));

        // b)
        System.out.println(DateExercises.ofYearDay(2016, 68));

        // c)
        System.out.println(DateExercises.specificDigitsSumBetweenHoursCounter(LocalTime.of(11, 45), LocalTime.of(22, 30), 15));

        //d
        System.out.println(DateExercises.howMany29FebInLife(LocalDate.of(1997, 4, 24)));
    }
}
