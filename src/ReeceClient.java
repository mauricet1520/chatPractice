import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by crci1 on 1/17/2017.
 */
public class ReeceClient {
    static String printFromServer;
    Socket socket;
    BufferedReader reader;
    PrintWriter out;

    public static void main(String[] args) {
        try {
                     new ReeceClient().sendMessage(null);

        } catch (IOException e) {

        }

    }

    public ReeceClient() {
        try {
            socket = new Socket("localhost", 8009); //create the connection
            out = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public String sendMessage(String message) throws IOException {
        out.println(message);
        System.out.println("Message sent");
        String messageFromServer = reader.readLine();
        System.out.println(messageFromServer);

        return messageFromServer;
    }
}
