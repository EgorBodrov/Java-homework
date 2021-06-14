package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.util.Duration;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        try {
//            Group group = new Group();
//            Scene scene = new Scene(group);
//            stage.setScene(scene);
//            stage.setWidth(540);
//            stage.setHeight(540);
//
//            stage.show();
//
//            Rectangle r = new Rectangle(0, 0, 40, 40);
//            group.getChildren().add(r);
//            TranslateTransition trans = new TranslateTransition();
//            trans.setNode(r);
//            trans.setDuration(Duration.millis(2000));
//
//
//            trans.play();

            Parent welcome = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene welcomeScene = new Scene(welcome);
            stage.setScene(welcomeScene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
