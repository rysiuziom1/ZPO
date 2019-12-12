import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class MyRunnable implements Runnable {
    private static Queue<Integer> queue = new ConcurrentLinkedQueue<>();
    private static int threadsCount = 0;
    private String string;
    private int positionInString = 0;
    private int threadIndex;
    private static final Object lock = new Object();

    public MyRunnable(AtomicInteger atomicInteger, String string) {
        this.string = string;
        this.threadIndex = atomicInteger.getAndIncrement();
        threadsCount++;
    }

    @Override
    public void run() {
        synchronized (lock) {
            queue.offer(threadIndex);
            if (queue.size() < threadsCount) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else lock.notifyAll();
        }

        synchronized (lock) {
            while (positionInString < string.length()) {
                int tmp = queue.peek();
                if (threadIndex != tmp) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (threadIndex == tmp) {
                    queue.poll();
                    queue.add(tmp);
                    System.out.print(string.charAt(positionInString));
                    lock.notifyAll();
                    positionInString++;
                }
            }
            queue.remove(threadIndex);
        }
    }
}
