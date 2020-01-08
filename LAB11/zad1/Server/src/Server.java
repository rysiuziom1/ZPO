import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(5050);
        System.out.println("Server started");
        System.out.println("Waiting for a clients");

        int count = 0;
        while (count < 3) {
            Socket client = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
            String clientName = dataInputStream.readUTF();
            System.out.println("Client >>" + clientName + "<< connected.");
            ClientHandler clientHandler = new ClientHandler(client, clientName);
            clientHandler.start();
            count++;
        }
    }
}
