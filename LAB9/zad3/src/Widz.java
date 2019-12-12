import java.util.Queue;
import java.util.Random;

public class Widz implements Runnable {
    private static final double probability = 0.3;
    private static final Random rand = new Random();
    private Queue<Widz> queue;

    public Widz(Queue<Widz> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(rand.nextDouble() <= probability)
            queue.poll();
    }
}
