import javafx.scene.canvas.GraphicsContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by crci1 on 1/17/2017.
 */
public class ServerThread implements Runnable {
    public final static String SERVER_MESSAGE_MARKER = "REECE_ECHO::";
    ServerSocket serverSocket = null;
    Socket sock = null;
    PrintWriter writer;
    BufferedReader inputFromClient;
    GraphicsContext graphicsContext;

    public ServerThread(ServerSocket serverSocket, Socket sock, GraphicsContext graphicsContext) {
        this.serverSocket = serverSocket;
        this.sock = sock;
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(8009);
            sock = serverSocket.accept();
            inputFromClient = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            writer = new PrintWriter(sock.getOutputStream(), true);

            String input;
            while ((input = inputFromClient.readLine()) != null) {
                System.out.println("message received on server: " + input);
                if (input.equals("exit")) {
                    break;
                }
                String paintArray[] = input.split(" ");
                Double xPlacement = Double.parseDouble(paintArray[0]);
                Double yPlacement = Double.parseDouble(paintArray[1]);
                Double strokeSize = Double.parseDouble(paintArray[2]);

                System.out.println("about to draw on server's canvas");
                if (graphicsContext != null) {
                    graphicsContext.strokeOval(xPlacement, yPlacement, strokeSize, strokeSize);
                }

                System.out.println(input);
                writer.println(SERVER_MESSAGE_MARKER + input);
            }
            writer.close();
            System.out.println("Incoming connection from " + sock.getInetAddress().getHostAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
