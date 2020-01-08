import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LottoParser {
    private static final LocalDate firstDraw = LocalDate.parse("27-01-1957", DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    public static List<Integer> getResultFromDate(LocalDate date, String url) throws IOException, IncorrectDateException {
        return getResultFromDate(date, date, url);
    }

    public static List<Integer> getResultFromDate(LocalDate startDate, LocalDate endDate, String url) throws IOException, IncorrectDateException {
        if(startDate.compareTo(firstDraw) < 0 || startDate.compareTo(endDate) > 0)
            throw new IncorrectDateException();
        if(endDate.compareTo(LocalDate.now()) > 0)
            endDate = LocalDate.now();
        List<Integer> numbers = new ArrayList<>();
        Connection connect = Jsoup.connect(url);
        Document document = connect.get();
        if(!url.contains("keno")) {
            String startMonthName = document
                    .selectFirst("#przedzial_czasu > div:nth-child(1) > select.form_miesiac > option:nth-child(" +
                            +startDate.getMonthValue() + ")").text();
            String endMonthName = document
                    .selectFirst("#przedzial_czasu > div.dolny_select > select.form_miesiac > option:nth-child(" +
                            +endDate.getMonthValue() + ")").text();
            String startDateString = startDate.getDayOfMonth() + "-" + startMonthName + "-" + startDate.getYear();
            String endDateString = endDate.getDayOfMonth() + "-" + endMonthName + "-" + endDate.getYear();
            String newUrl = url + "/losowania-od-" + startDateString + "-do-" + endDateString;
            connect = Jsoup.connect(newUrl);
        }
        else {
            String newUrl = url + "/losowania-z-dnia-" + startDate.getDayOfMonth() + "." + startDate.getMonthValue() + "." + startDate.getYear();
            connect = Jsoup.connect(newUrl);
        }
        document = connect.get();
        Elements elements = document.select("li.numbers_in_list, li.numbers_in_list_new_line > span.pierwsza_liczba_w_nowym_wierszu");
        elements.forEach(e -> numbers.add(Integer.parseInt(e.text())));
        return numbers;
    }

    public static Map<Integer, Integer> getHistogramFromYear(int year, String url) throws IncorrectDateException, IOException {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        return getHistogramBetweenDates(startDate, endDate, url);
    }

    public static Map<Integer, Integer> getHistogramBetweenDates(LocalDate startDate, LocalDate endDate, String url) throws IOException, IncorrectDateException {
        List<Integer> numbers = getResultFromDate(startDate, endDate, url);
        Map<Integer, Integer> hist = new HashMap<>();
        numbers.forEach(e -> hist.merge(e, 1, Integer::sum));
        return hist;
    }
}
