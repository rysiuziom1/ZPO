import java.util.ArrayList;
import java.util.List;

public class MaxSearchAlgorithms {


    List<Integer> leftMaxScan(int[] data) {
        List<Integer> listOfMaxes = new ArrayList<>();
        int max = data[0];
        listOfMaxes.add(max);
        for(int i = 1; i < data.length; i++) {
            if(max < data[i]) {
                max = data[i];
                listOfMaxes.add(max);
            }
        }
        return listOfMaxes;
    }

    List<Integer> rightMaxScan(int[] data) {
        List<Integer> listOfMaxes = new ArrayList<>();
        int max = data[data.length - 1];
        listOfMaxes.add(max);
        for(int i = data.length - 2; i >= 0; i--) {
            if(max < data[i]) {
                max = data[i];
                listOfMaxes.add(max);
            }
        }
        return listOfMaxes;
    }

    List<Integer> evenOddMaxScan(int[] data) {
        List<Integer> listOfMaxes = new ArrayList<>();
        int max = data[0];
        listOfMaxes.add(max);
        for(int i = 2; i < data.length; i += 2) {
            if(max < data[i]) {
                max = data[i];
                listOfMaxes.add(max);
            }
        }

        for(int i = 1; i < data.length; i += 2) {
            if(max < data[i]) {
                max = data[i];
                listOfMaxes.add(max);
            }
        }

        return listOfMaxes;
    }
}
