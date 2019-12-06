import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private final static int n = 1_000_000_000;
    private final static int sqrtN = (int) Math.sqrt(n);

    public static void main(String[] args) throws InterruptedException {
        boolean[] A = new boolean[n + 1];
        Arrays.fill(A, true);
        Set<Integer> smallPrimes = initSmallPrimes();
        Iterator<Integer> iterator = smallPrimes.iterator();
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
//        executorService.execute(new ThreadA(A, iterator));
//        executorService.execute(new ThreadB(A, iterator));
        executorService.execute(new ThreadC(A, smallPrimes));
        executorService.execute(new ThreadC(A, smallPrimes));
        executorService.execute(new ThreadC(A, smallPrimes));
        executorService.execute(new ThreadC(A, smallPrimes));
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        long executionTime = System.currentTimeMillis() - startTime;
        int count = 0;
        int oneMillionCount = 0, tenMillionCount = 0, hundredMillionCount = 0;
        List<Integer> oneMillionList = new ArrayList<>();
        for (int i = 2; i < 1_000_000_000; i++) {
            if (A[i]) count++;
            if(i < 1_000_000) oneMillionList.add(i);
            else if(i == 1_000_000) oneMillionCount = count;
            else if(i == 10_000_000) tenMillionCount = count;
            else if(i == 100_000_000) hundredMillionCount = count;
        }
        System.out.println("1 Million: " + oneMillionCount);
        System.out.println("10 Million: " + tenMillionCount);
        System.out.println("100 Million: " + hundredMillionCount);
        System.out.println("1 Billion: " + count);
        System.out.println("Czas wykonania: " + executionTime / 1000d + "s");

    }

    private static Set<Integer> initSmallPrimes() {
        boolean[] A = new boolean[sqrtN + 1];
        Arrays.fill(A, true);
        for (int i = 2; i <= Math.sqrt(sqrtN); i++) {
            if (A[i]) {
                for (int j = 2 * i; j <= sqrtN; j += i) {
                    A[j] = false;
                }
            }
        }

        Set<Integer> smallPrimes = new LinkedHashSet<>();

        for (int i = 2; i <= sqrtN; i++) {
            if (A[i]) smallPrimes.add(i);
        }

        return smallPrimes;
    }
}
