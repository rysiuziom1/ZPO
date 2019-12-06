import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MyRunnable implements Runnable {
    static List<Integer> queue = new ArrayList<>();
    static int queueIndex = 0;
    static Object lock = new Object();
    private String string;
    private int index = 0;
    AtomicInteger atomic;
    private int stringIndex = 0;
    private static int threadsCount = 0;

    MyRunnable(AtomicInteger atomic, String string) {
        this.string = string;
        this.atomic = atomic;
        threadsCount++;
    }

    @Override
    public void run() {
        synchronized (lock) {
            index = atomic.getAndIncrement();
            queue.add(index);
            if(index == threadsCount - 1)
                lock.notifyAll();
            else {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        while (stringIndex < string.length()) {
            synchronized (lock) {
                if (queue.get(queueIndex) != index) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (queue.get(queueIndex) == index) {
                    System.out.print(string.charAt(stringIndex));
                    stringIndex++;
                    queueIndex++;
                    if (queueIndex >= queue.size())
                        queueIndex = 0;
                    lock.notifyAll();
                }
            }
        }

        synchronized (this) {
            if(queue.get(queueIndex) == index) {
                queueIndex++;
                if (queueIndex >= queue.size())
                    queueIndex = 0;
                lock.notifyAll();
            }
        }
    }
}
