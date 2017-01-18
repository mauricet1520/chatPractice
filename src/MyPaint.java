import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.io.IOException;

/**
 * Created by crci1 on 1/17/2017.
 */
public class MyPaint extends Application {
    double strokeSize = 2;


    @Override
    public void start(Stage primaryStage) throws Exception {
        ReeceClient reeceClient = new ReeceClient();
        Group root = new Group();
        Canvas canvas = new Canvas(800, 800);
        canvas.setFocusTraversable(true);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.BLUE);

        root.getChildren().add(canvas);

        primaryStage.setTitle("My Paint");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();

        graphicsContext.setLineWidth(2);

        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("x: " + e.getX() + ", y: " + e.getY());
                graphicsContext.strokeOval(e.getX(), e.getY(), strokeSize, strokeSize);
                try {
                    reeceClient.sendMessage(e.getX() + " " + e.getY() + " " + strokeSize);
                } catch (IOException io) {
                    io.printStackTrace();
                }

            }
        });

        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("Hello keyEvent");
//                        if (e.getText().equals("d")){
//                            drawingFlag = !drawingFlag;
//                        }
                if (event.getText().equals("a")) {
                    Color myColor = Color.color(Math.random(), Math.random(), Math.random());
                    graphicsContext.setStroke(myColor);
                }
                if (event.getText().equals("c")) {
                    graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                }
                if (event.getCode().toString().equals("UP") && strokeSize <= 20) {
                    strokeSize++;

                }
                if (event.getCode().toString().equals("DOWN") && strokeSize >= 2) {
                    strokeSize--;
                }
                System.out.println(event.getCode().toString());
            }
        });

    }

    public static void main(String[] args) {

        launch(args);

    }
}
