import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        String[] strings = {"aaaa", "bb", "ccccccccccccc", "dddddd"};
        AtomicInteger atomicInteger = new AtomicInteger(0);

        MyRunnable t1 = new MyRunnable(atomicInteger, strings[0]);
        MyRunnable t2 = new MyRunnable(atomicInteger, strings[1]);
        MyRunnable t3 = new MyRunnable(atomicInteger, strings[2]);
        MyRunnable t4 = new MyRunnable(atomicInteger, strings[3]);

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.execute(t1);
        executorService.execute(t2);
        executorService.execute(t3);
        executorService.execute(t4);
    }
}
