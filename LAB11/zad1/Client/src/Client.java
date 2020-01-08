import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class Client {
    public static void main(String[] args) throws InterruptedException, IOException {
        String name = args[0];
        System.out.println(name);
        Socket socket = new Socket("localhost", 5050);
        System.out.println("Connected to server");
        String route = "S                   |       |         |                |           |      M";
        String bike = "o&o";
        System.out.println(route);
        System.out.print(bike + "\r");
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        outputStream.writeUTF(name);
        while(!inputStream.readUTF().equals("START"));
        while (bike.length() <= route.length()) {
            System.out.print("\r" + bike);
            if(route.charAt(bike.length() - 1) == '|')
                outputStream.writeUTF("FINISH");
            else if(route.charAt(bike.length() - 1) == 'M')
                outputStream.writeUTF("META");
            Thread.sleep((new Random().nextInt(12) + 3) * 10);
            bike = "." + bike;
        }
        System.out.println("\n" + name + " nr " + inputStream.readUTF() + " na mecie");
    }
}
