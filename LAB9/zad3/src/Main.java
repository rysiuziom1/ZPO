import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Widz> list = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for(int i = 0; i < 100; i++) {
            executorService.execute(new Osoba(list));
        }
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        System.out.println("Ilość widzów na początku: " + list.size());
        if(list.size() < 5)
            System.out.println("Przepraszamy, filmu nie będzie");
        else {
            Runnable kino = () -> {
                ExecutorService service = Executors.newCachedThreadPool();
                for(Widz widz : list) {
                    service.execute(widz);
                }
                service.shutdown();
                try {
                    service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(list.size() < 5) {
                    System.out.println("BANDA FRAJERÓW! PIENIĄDZE NIE ZOSTANĄ ZWRÓCONE!\nZbyt mało widzów(" + list.size() +")");
                    Thread.yield();
                } else {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Koniec seansu\nWidzów: " + list.size());
                }
            };
            new Thread(kino).start();
        }
    }
}
