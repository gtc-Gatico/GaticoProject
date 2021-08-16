package cn.com.gatico.窗体;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class TestSunSwing extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("文件管理");
        primaryStage.setWidth(400);
        primaryStage.setHeight(400);
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(400, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(primaryStage,gc);
        canvas.setOnMouseClicked(event -> {
            System.out.println("点击了canvas");
        });
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void drawShapes(Stage primaryStage,GraphicsContext gc){
        new Thread(() -> {
            while(true){
                gc.clearRect(0,0,primaryStage.getWidth(),primaryStage.getHeight());

                gc.save();
                Rotate r = new Rotate(System.currentTimeMillis()%360, 100 +20/2,100+20/2);
                gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
                gc.setStroke(Color.BLUE);
                gc.fillRect(100,100,20,20);
                gc.restore();

                gc.save();
                r = new Rotate(System.currentTimeMillis()%360, 200 +20/2,100+20/2);
                gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
                gc.setStroke(Color.BLUE);
                gc.fillRoundRect(200,100,20,20,5,5);
                gc.restore();

                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }


}
