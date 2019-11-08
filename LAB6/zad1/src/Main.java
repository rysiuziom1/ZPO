import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("dane.txt");
        Integer sumOfTwoLowestSalaries = Files.lines(path, StandardCharsets.UTF_8)
                .filter(s -> s.split(" ")[2].equalsIgnoreCase("PL"))
                .map(s -> Integer.parseInt(s.split(" ")[3]))
                .sorted(Integer::compareTo)
                .limit(2)
                .reduce(0, Integer::sum);
        System.out.println("Sum of 2 lowest salaries: " + sumOfTwoLowestSalaries);

        Integer sumOfTwoHighestSalaries = Files.lines(path, StandardCharsets.UTF_8)
                .filter(s -> s.split(" ")[2].equalsIgnoreCase("PL"))
                .map(s -> Integer.parseInt(s.split(" ")[3]))
                .sorted((i1, i2) -> Integer.compare(i2, i1))
                .limit(2)
                .reduce(0, Integer::sum);
        System.out.println("Sum of 2 highest salaries: " + sumOfTwoHighestSalaries);

        Map<String, Integer> countryCount = Files.lines(path, StandardCharsets.UTF_8)
                .map(s -> s.split(" ")[2])
                .map(s -> new AbstractMap.SimpleEntry<>(s, 1))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum, LinkedHashMap::new));
        System.out.println(countryCount);

    }
}
