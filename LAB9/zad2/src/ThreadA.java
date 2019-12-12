import java.util.Iterator;

public class ThreadA implements Runnable {
    private boolean[] primes;
    private static Iterator<Integer> iterator;
    private int n;

    public ThreadA(boolean[] primes, Iterator<Integer> iterator) {
        this.primes = primes;
        ThreadA.iterator = iterator;
        this.n = primes.length - 1;
    }


    @Override
    public void run() {
        while (iterator.hasNext()) {
            int index = iterator.next();
            for (int i = 2 * index; i <= n; i += index)
                primes[i] = false;
        }
    }
}
