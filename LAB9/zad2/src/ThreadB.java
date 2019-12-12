import java.util.Iterator;

public class ThreadB implements Runnable {
    private static int threadsCount = 0;
    private boolean[] primes;
    private final Iterator<Integer> iterator;
    private int n;
    private int threadIndex;
    private static int index;
    private static final Object lock = new Object();
    private static int currentThread = 0;

    public ThreadB(boolean[] primes, Iterator<Integer> iterator) {
        this.primes = primes;
        this.iterator = iterator;
        this.n = primes.length - 1;
        this.threadIndex = threadsCount;
        threadsCount++;
    }

    @Override
    public void run() {
        synchronized (lock) {
            while (iterator.hasNext() || currentThread != 0) {
                if (currentThread != threadIndex) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    currentThread++;
                    if (currentThread == threadsCount)
                        currentThread = 0;
                    if (threadIndex == 0) {
                        index = iterator.next();
                    }
                    lock.notifyAll();
                    if (index != 2) {
                        for (int i = index * index + 2 * threadIndex * index; i <= n; i += 2 * threadsCount * index)
                            primes[i] = false;
                    } else {
                        for (int i = index * index + threadIndex * index; i <= n; i += threadsCount * index)
                            primes[i] = false;
                    }
                }
            }
        }
    }
}
