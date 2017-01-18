import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by crci1 on 1/17/2017.
 */
public class MySecondPaint extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        System.out.println("Server is listening");

        Group root  = new Group();
        Canvas canvas = new Canvas(800, 800);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.GREEN);

        ReeceServer reeceServer = new ReeceServer(graphicsContext);
        reeceServer.startServer();

        root.getChildren().add(canvas);

        primaryStage.setTitle("My Second Paint");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();

        graphicsContext.strokeOval(100, 100, 20, 20);

        System.out.println("Done with starting MySecondPaint");

    }

    public static void main(String[] args) {
        launch(args);
    }
}
