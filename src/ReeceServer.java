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
public class ReeceServer {
    ServerSocket serverSocket;
    Socket sock;
    GraphicsContext graphicsContext;

    public ReeceServer(GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;
    }

    public void startServer() {

            System.out.println("In startServer() ...");
            System.out.println("About to wait for a connection ...");
            Thread myThread = new Thread(new ServerThread(serverSocket, sock, graphicsContext));
            myThread.start();
            System.out.println("Connection accepted!");


    }

}
