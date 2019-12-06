import java.util.Set;

public class ThreadC implements Runnable {
    private volatile boolean[] A;
    private Set<Integer> smallPrimes;
    private static int threadsCount = 0;
    private int n;
    private int threadIndex;

    public ThreadC(boolean[] A, Set<Integer> smallPrimes) {
        this.A = A;
        this.smallPrimes = smallPrimes;
        this.threadIndex = threadsCount;
        threadsCount++;
        this.n = A.length - 1;
    }

    @Override
    public void run() {
        int start = n / threadsCount * threadIndex;
        int end = start + n / threadsCount;
        for (Integer smallPrime : smallPrimes) {
            int low = start / smallPrime * smallPrime;
            if (low < start) low += smallPrime;
            if (low == start) low += smallPrime;
            for (int i = low + smallPrime; i <= end; i += smallPrime) {
                A[i] = false;
            }
        }
    }
}
