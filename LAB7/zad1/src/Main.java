import org.jacop.constraints.XneqY;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.core.Var;
import org.jacop.search.*;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        List<String> countriesList = new ArrayList<>(Arrays.asList("Polska", "Białoruś", "Ukraina", "Słowacja", "Czechy",
                "Austria", "Węgry", "Niemcy", "Dania"));
        Map<Integer, String> colorsMap = new HashMap<>();
        colorsMap.put(1, "czerwony");
        colorsMap.put(2, "czarny");
        colorsMap.put(3, "niebieski");
        colorsMap.put(4, "zielony");
        colorsMap.put(5, "żółty");

        int size = 9;
        IntVar[] v = new IntVar[size];
        for (int i = 0; i < size; i++) {
            v[i] = new IntVar(store, countriesList.get(i), 1, 5);
        }

        store.impose(new XneqY(v[0], v[1]));
        store.impose(new XneqY(v[0], v[2]));
        store.impose(new XneqY(v[0], v[3]));
        store.impose(new XneqY(v[0], v[4]));
        store.impose(new XneqY(v[0], v[7]));
        store.impose(new XneqY(v[1], v[2]));
        store.impose(new XneqY(v[2], v[3]));
        store.impose(new XneqY(v[2], v[6]));
        store.impose(new XneqY(v[3], v[4]));
        store.impose(new XneqY(v[3], v[5]));
        store.impose(new XneqY(v[3], v[6]));
        store.impose(new XneqY(v[4], v[5]));
        store.impose(new XneqY(v[4], v[7]));
        store.impose(new XneqY(v[5], v[6]));
        store.impose(new XneqY(v[5], v[7]));
        store.impose(new XneqY(v[7], v[8]));

        Search<IntVar> search = new DepthFirstSearch<>();
        SelectChoicePoint<IntVar> select = new InputOrderSelect<>(store, v, new IndomainMin<>());
        boolean result = search.labeling(store, select);

        if (result) {
            for (int i = 0; i < size; i++) {
                System.out.println(countriesList.get(i) + ": " + colorsMap.get(v[i].value()));
            }
        } else
            System.out.println("nope");

        int[][] adjacencyMatrix = new int[9][9];
        store.getConstraints().forEach(e -> {
            List<Integer> tmp = e.arguments().stream().map(Var::index).collect(Collectors.toList());
            adjacencyMatrix[tmp.get(0)][tmp.get(1)] = 1;
            adjacencyMatrix[tmp.get(1)][tmp.get(0)] = 1;
        });

        System.out.println();
        System.out.printf("%10s", " ");
        countriesList.forEach(e -> System.out.printf("%10s", e));
        System.out.println();
        for (int i = 0; i < 9; i++) {
            System.out.printf("%10s", countriesList.get(i));
            for (int j = 0; j < 9; j++)
                System.out.printf("%10s", adjacencyMatrix[i][j]);
            System.out.println();
        }

        System.out.println();
        System.out.printf("%10s", " ");
        countriesList.forEach(e -> System.out.printf("%10s", e));
        System.out.println();
        for (int i = 0; i < 9; i++) {
            System.out.printf("%10s", countriesList.get(i));
            for (int j = 0; j < 9; j++) {
                System.out.printf("%10s", adjacencyMatrix[i][j] == 1 ? "x" : ".");
            }
            System.out.println();
        }
    }
}
