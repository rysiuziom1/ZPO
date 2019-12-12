import java.util.Iterator;
import java.util.Set;

public class ThreadC implements Runnable {
    private boolean[] primes;
    private final Iterator<Integer> iterator;
    private static int threadsCount = 0;
    private int n;
    private int threadIndex;

    public ThreadC(boolean[] primes, Iterator<Integer> iterator) {
        this.primes = primes;
        this.iterator = iterator;
        this.threadIndex = threadsCount;
        threadsCount++;
        this.n = primes.length - 1;
    }

    @Override
    public void run() {
        int start = n / threadsCount * threadIndex;
        int end = start + n / threadsCount;
        if(start < 2) start = 2;
        System.out.println(start + " " + end);
        while(iterator.hasNext()) {
            int smallPrime = iterator.next();
            int low = (start / smallPrime) * smallPrime;
            if(low < start) low += smallPrime;
            if(start == 2) {
                for (int i = low + smallPrime; i <= end; i += smallPrime) {
                    primes[i] = false;
                }
            }
            else {
                for (int i = low; i <= end; i += smallPrime) {
                    primes[i] = false;
                }
            }
        }
    }
}
