import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientHandler extends Thread {
    private static volatile int count = 0;
    private Socket socket;
    private String clientName;
    private static AtomicInteger finish = new AtomicInteger(1);
    private static AtomicInteger place = new AtomicInteger(1);

    public ClientHandler(Socket socket, String clientName) {
        this.socket = socket;
        this.clientName = clientName;
        count++;
    }

    public void run() {
        try {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            String clientMessage = "", serverMessage = "";
            int finishCount = 1;
            while(count < 3);
            serverMessage = "START";
            dataOutputStream.writeUTF(serverMessage);
            while(!clientMessage.equals("META")) {
                clientMessage = dataInputStream.readUTF();
                if(clientMessage.equals("FINISH")) {
                    if(finish.get() == finishCount) {
                        System.out.println(clientName + " wygrał lotny finisz nr " + finishCount);
                        finish.getAndIncrement();
                    }
                    finishCount++;
                }
            }
            if(place.get() == 1) {
                System.out.println(clientName + " pierwszy na mecie");
            }
            dataOutputStream.writeUTF(Integer.toString(place.getAndIncrement()));
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
