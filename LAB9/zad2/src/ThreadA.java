import java.util.Iterator;

public class ThreadA implements Runnable {
    private boolean[] A;
    private Iterator<Integer> iterator;
    private int n;

    public ThreadA(boolean[] A, Iterator<Integer> iterator) {
        this.A = A;
        this.iterator = iterator;
        this.n = A.length - 1;
    }


    @Override
    public void run() {
        while (iterator.hasNext()) {
            int index = iterator.next();
            for (int i = 2 * index; i <= n; i += index)
                A[i] = false;
        }
    }
}
