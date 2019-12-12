import java.util.Queue;
import java.util.Random;

public class Osoba implements Runnable{
    private int time;
    private static final double probability = 0.05;
    private static final Random rand = new Random();
    private Queue<Widz> queue;

    public Osoba(Queue<Widz> queue) {
        this.time = rand.nextInt(3) + 1;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(rand.nextDouble() <= probability) {
            queue.offer(new Widz(queue));
        }
    }
}
