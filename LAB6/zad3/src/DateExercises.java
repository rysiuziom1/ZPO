import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class DateExercises {
    public static long daysBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    public static LocalDate ofYearDay(int year, int dayOfYear) {
        return LocalDate.ofYearDay(year, dayOfYear);
    }

    public static long specificDigitsSumBetweenHoursCounter(LocalTime startTime, LocalTime endTime, int expectedSumOfDigits) {
        long count = 0;
        while (!startTime.isAfter(endTime)) {
            int sumOfDigits = 0, hour = startTime.getHour(), minute = startTime.getMinute();
            sumOfDigits += hour % 10 + hour / 10 + minute % 10 + minute / 10;
            if (sumOfDigits == expectedSumOfDigits) count++;
            startTime = startTime.plusMinutes(1);
        }
        return count;
    }

    public static short howMany29FebInLife(LocalDate birthDate, LocalDate endDate) {
        short count = 0;
        LocalDate year = LocalDate.of(birthDate.getYear(), 3, 1);
        if (birthDate.getMonthValue() >= 3) year = year.plusYears(1);
        while (year.isBefore(endDate)) {
            if (year.isLeapYear()) count++;
            year = year.plusYears(1);
        }
        return count;
    }

    public static short howMany29FebInLife(LocalDate birthDate) {
        return howMany29FebInLife(birthDate, LocalDate.now());
    }
}
