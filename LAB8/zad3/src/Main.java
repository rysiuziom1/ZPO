public class Main {
    public static void main(String[] args) {
        String[] strings = {"aaaa", "bb", "ccccccccccccc", "dddddd"};

        PrintSequenceRunnable task1 = new PrintSequenceRunnable(3, strings[0]);
        PrintSequenceRunnable task2 = new PrintSequenceRunnable(2, strings[1]);
        PrintSequenceRunnable task3 = new PrintSequenceRunnable(0, strings[2]);
        PrintSequenceRunnable task4 = new PrintSequenceRunnable(1, strings[3]);

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        Thread t3 = new Thread(task3);
        Thread t4 = new Thread(task4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
