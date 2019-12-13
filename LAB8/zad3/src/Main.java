import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final int threadsCount = 4;

    public static void main(String[] args) {
        String[] strings = {"aaaa", "bb", "ccccccccccccc", "dddddd"};
        AtomicInteger atomicInteger = new AtomicInteger(0);

        List<Callable<String>> myCallableList = new ArrayList<>();

        for (int i = 0; i < threadsCount; i++) {
            int finalI = i;
            myCallableList.add(() -> {
                new MyRunnable(atomicInteger, strings[finalI]).run();
                return strings[finalI];
            });
        }

        ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);
        try {
            executorService.invokeAll(myCallableList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
