public class HelloTask implements Runnable {
    private int threadIndex;
    private static int threadsCount = 0;
    private static final Object lock = new Object();

    public HelloTask() {
        this.threadIndex = threadsCount;
        threadsCount++;
    }

    @Override
    public void run() {
        synchronized (lock) {
            while(threadIndex != threadsCount - 1) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Hello from task " + threadIndex);
            threadsCount--;
            lock.notifyAll();
        }
    }
}
