public class PrintSequenceRunnable implements Runnable{
    public int PRINT_NUMBERS_UPTO=100;
    static int  number=1;
    int remainder;
    static Object lock=new Object();
    private String string;
    private int index = 0;

    PrintSequenceRunnable(int remainder, String string)
    {
        this.remainder=remainder;
        this.string = string;
    }

    @Override
    public void run() {
        while (number < PRINT_NUMBERS_UPTO-1) {
            synchronized (lock) {
                while (number % 4 != remainder) { // wait for numbers other than remainder
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(index < string.length()) {
                    System.out.print(string.charAt(index));
                    index++;
                }

                number++;
                lock.notifyAll();
            }
        }
    }
}
