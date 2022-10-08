package cn.com.gatico.from;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import javax.swing.plaf.multi.MultiButtonUI;

public class app extends Application {
    @Override
    public void start(Stage stage) throws Exception {
//        Parent p = FXMLLoader.load();
//        Scene scene1 = new Scene();
//        HostInfo hostInfo = new HostInfo("","",0);

        Circle circ = new Circle(40, 40, 30);
        Group root = new Group(circ);
        Scene scene = new Scene(root, 400, 300);

        stage.setTitle("My JavaFX Application");
        stage.setScene(scene);
        stage.show();
        System.out.println(System.currentTimeMillis());
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        Application.launch(args);

        MultiButtonUI multiButtonUI = new MultiButtonUI();
    }
}
