import javafx.scene.canvas.GraphicsContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by crci1 on 1/17/2017.
 */
public class ReeceClientTest {
    ReeceClient reeceClient;
    ReeceServer reeceServer;

    @Before
    public void setUp() throws Exception {
        reeceServer = new ReeceServer(null);
        reeceServer.startServer();
        reeceClient = new ReeceClient();
        System.out.println("server initialized");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testMutipleMessages() throws Exception{

        String messageSending = "12.0 25.0";
        String secondMessage = "15.89 54.36";
        String serverResponse = reeceClient.sendMessage(messageSending);
        assertEquals(ServerThread.SERVER_MESSAGE_MARKER + messageSending, serverResponse);

        serverResponse = reeceClient.sendMessage(secondMessage);
        assertEquals(ServerThread.SERVER_MESSAGE_MARKER + secondMessage, serverResponse);
        System.out.println("done with testConnection()");

    }



}