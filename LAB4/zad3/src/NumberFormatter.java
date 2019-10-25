import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class NumberFormatter {
    static List<String> formattedNumbers(List<Double> nums, int group, char separator, int nDigits, boolean padding) {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setGroupingSeparator(separator);
        decimalFormatSymbols.setDecimalSeparator('.');

        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        decimalFormat.setGroupingSize(group);

        List<String> stringNums = nums.stream().map(Double::intValue).map(decimalFormat::format).collect(Collectors.toList());
        String max = Collections.max(stringNums, Comparator.comparing(String::length));

        decimalFormat.setMaximumFractionDigits(nDigits);
        if (padding)
            decimalFormat.setMinimumFractionDigits(nDigits);

        List<String> returnList = new ArrayList<>();
        
        for (int i = 0; i < nums.size(); i++) {
            int j = stringNums.get(i).length();
            StringBuilder sb = new StringBuilder();
            while (j < max.length()) {
                sb.append(" ");
                j++;
            }
            returnList.add(sb.toString() + decimalFormat.format(nums.get(i)));
        }
        return returnList;
    }
}
