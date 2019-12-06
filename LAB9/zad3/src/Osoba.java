import java.util.List;
import java.util.Random;

public class Osoba implements Runnable{
    private int time;
    private static final double probability = 0.05;
    private static final Random rand = new Random();
    private List<Widz> list;

    public Osoba(List<Widz> list) {
        this.time = rand.nextInt(3) + 1;
        this.list = list;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(rand.nextDouble() <= probability) {
            list.add(new Widz(list));
        }
    }
}
