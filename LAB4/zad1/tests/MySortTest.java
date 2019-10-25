import org.junit.Before;
import org.junit.Test;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;

import static org.junit.Assert.*;

public class MySortTest {
    private String[] names;
    private String[] names2;
    private Collator collator = Collator.getInstance(new Locale("pl", "PL"));

    private void createNamesArrays() {
        names = new String[]{"Łukasz", "Ścibor", "Stefania", "Darek", "Agnieszka",
                "Zyta", "Órszula", "Świętopełk"};
        names2 = Arrays.copyOf(names, names.length);
    }

    private void createNamesArray() {
        names = new String[]{"Łukasz", "Ścibor", "Stefania", "Darek", "Agnieszka",
                "Zyta", "Órszula", "Świętopełk"};
    }

    @Test
    public void testInsertionSort() {
        createNamesArrays();
        Arrays.sort(names, collator);
        MySort.sortStrings(collator, names2);
        assertArrayEquals(names, names2);
    }

    @Test
    public void sortsCompatibilityTest() {
        createNamesArrays();
        MySort.sortStrings(collator, names);
        MySort.fastSortStrings(collator, names2);
        System.out.println("sortStrings: " + Arrays.toString(names) + "\nfastSortStrings: " + Arrays.toString(names2));
        assertArrayEquals(names, names2);
        createNamesArrays();
        MySort.sortStrings(collator, names);
        MySort.fastSortStrings2(collator, names2);
        System.out.println("\nsortStrings: " + Arrays.toString(names) + "\nfastSortStrings2: " + Arrays.toString(names2));
        assertArrayEquals(names, names2);
        createNamesArrays();
        MySort.fastSortStrings(collator, names);
        MySort.fastSortStrings2(collator, names2);
        System.out.println("\nfastSortStrings: " + Arrays.toString(names) + "\nfastSortStrings2: " + Arrays.toString(names2));
        assertArrayEquals(names, names2);
    }

    @Test
    public void performanceSortStringsTest() {
        createNamesArray();
        MySort.sortStrings(collator, names);
        long startTime = System.nanoTime();
        for(int i = 0; i < 100000; i++) {
            createNamesArray();
            MySort.sortStrings(collator, names);
        }
        long endTime = System.nanoTime();
        System.out.print("Time for sortStrings function: " + (endTime - startTime));
    }

    @Test
    public void performanceFastSortStringsTest() {
        createNamesArray();
        MySort.fastSortStrings(collator, names);
        long startTime = System.nanoTime();
        for(int i = 0; i < 100000; i++) {
            createNamesArray();
            MySort.fastSortStrings(collator, names);
        }
        long endTime = System.nanoTime();
        System.out.print("Time for fastSortStrings function: " + (endTime - startTime));
    }

    @Test
    public void performanceFastSortStrings2Test() {
        createNamesArray();
        MySort.fastSortStrings2(collator, names);
        long startTime = System.nanoTime();
        for(int i = 0; i < 100000; i++) {
            createNamesArray();
            MySort.fastSortStrings2(collator, names);
        }
        long endTime = System.nanoTime();
        System.out.print("Time for fastSortStrings2 function: " + (endTime - startTime));
    }
}