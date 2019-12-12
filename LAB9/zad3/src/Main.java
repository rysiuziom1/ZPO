import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

public class Main {
    private static final int personsCount = 100;
    private static final int minPersonsCount = 5;

    public static void main(String[] args) throws InterruptedException {
        List<Callable<Integer>> list = new ArrayList<>();
        Queue<Widz> queue = new ConcurrentLinkedQueue<>();

        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i = 0; i < personsCount; i++) {
            int finalI = i;
            list.add(() -> {
                new Osoba(queue).run();
                return finalI;
            });
        }
        executorService.invokeAll(list);
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        System.out.println("Ilość widzów na początku: " + queue.size());
        if(queue.size() < minPersonsCount)
            System.out.println("Przepraszamy, filmu nie będzie");
        else {
            Runnable kino = () -> {
                ExecutorService service = Executors.newCachedThreadPool();
                for(Widz widz : queue) {
                    service.execute(widz);
                }
                service.shutdown();
                try {
                    service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Ilość widzów w połowie: " + queue.size());
                if(queue.size() < minPersonsCount) {
                    System.out.println("BANDA FRAJERÓW! PIENIĄDZE NIE ZOSTANĄ ZWRÓCONE!");
                    Thread.yield();
                } else {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Koniec seansu");
                }
            };
            new Thread(kino).start();
        }
    }
}
