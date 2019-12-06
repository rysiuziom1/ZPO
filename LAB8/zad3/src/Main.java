import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        String[] strings = {"aaaa", "bb", "ccccccccccccc", "dddddd"};
        AtomicInteger atomicInteger = new AtomicInteger(0);

        MyRunnable task1 = new MyRunnable(atomicInteger, strings[0]);
        MyRunnable task2 = new MyRunnable(atomicInteger, strings[1]);
        MyRunnable task3 = new MyRunnable(atomicInteger, strings[2]);
        MyRunnable task4 = new MyRunnable(atomicInteger, strings[3]);

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        Thread t3 = new Thread(task3);
        Thread t4 = new Thread(task4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
