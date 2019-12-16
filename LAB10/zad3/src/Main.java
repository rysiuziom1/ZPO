import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Connection connect = Jsoup.connect("http://megalotto.pl/wyniki");
        Document document = connect.get();
        Elements allTr = document.select("#div_dla_tabeli_wyniki_gry > table > tbody > tr > td > a");
        for (int i = 0; i < allTr.size(); i++)
            System.out.println(i + ". " + allTr.get(i).text());
        System.out.print("Wybierz rodzaj losowania (0-9): ");
        Scanner scanner = new Scanner(System.in);
        int selected = Integer.parseInt(scanner.nextLine());
        Element element = allTr.get(selected);
        System.out.println("0. Wyniki z konkretnego dnia");
        System.out.println("1. Histogram liczb w danym roku");
        System.out.println("2. Histogram liczb w danym przedziale czasowym");
        System.out.print("Wybierz rodzaj danych (0-2): ");
        selected = Integer.parseInt(scanner.nextLine());
        switch (selected) {
            case 0:
                System.out.println("Podaj datę w formacie DD-MM-YYYY: ");
                String date = scanner.nextLine();
                LocalDate localDate = LocalDate.parse(date, formatter);
                try {
                    List<Integer> numbers = LottoParser.getResultFromDate(localDate, element.attr("href"));
                    System.out.println(numbers);
                } catch (IncorrectDateException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                System.out.println("Podaj rok: ");
                int year = Integer.parseInt(scanner.nextLine());
                try {
                    System.out.println(LottoParser.getHistogramFromYear(year, element.attr("href")));
                } catch (IncorrectDateException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                System.out.println("Podaj datę początkową w formacie DD-MM-YYYY: ");
                String startDateString = scanner.nextLine();
                System.out.println("Podaj datę końcową w formacie DD-MM-YYYY: ");
                String endDateString = scanner.nextLine();
                LocalDate startDate = LocalDate.parse(startDateString, formatter);
                LocalDate endDate = LocalDate.parse(endDateString, formatter);
                try {
                    System.out.println(LottoParser.getHistogramBetweenDates(startDate, endDate, element.attr("href")));
                } catch (IncorrectDateException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Błędne wejście!");
                break;
        }
    }
}
