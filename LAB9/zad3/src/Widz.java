import java.util.List;
import java.util.Random;

public class Widz implements Runnable {
    private static final double probability = 0.3;
    private static final Random rand = new Random();
    private List<Widz> list;

    public Widz(List<Widz> list) {
        this.list = list;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(rand.nextDouble() <= probability)
            list.remove(this);
    }
}
