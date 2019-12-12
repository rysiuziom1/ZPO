import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Thread> threadList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            threadList.add(new Thread(new HelloTask()));
        }
        threadList.forEach(Thread::start);
    }
}
