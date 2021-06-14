package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class WelcomeController {

    @FXML
    private TextField elevatorsTextField;
    @FXML
    private TextField capacityTextField;
    @FXML
    private TextField floorsTextField;
    @FXML
    private TextField peopleTextField;
    @FXML
    private AnchorPane scenePane;

    Stage stage, st;
    private int startX, startY, endX, endY;
    private ElevatorController ctrl;
    private Building house;
    private int elevators, capacity, floors, people;
    private int floorHeight = 60, width = 100;
    private int liftWidth = 40, liftHeight = 50;
    private ArrayList<Rectangle> lifts = new ArrayList<>();
    private ArrayList<Text> counter = new ArrayList<>();

    public void start(ActionEvent push) {
        try {
            elevators = Integer.parseInt(elevatorsTextField.getText());
            capacity = Integer.parseInt(capacityTextField.getText());
            floors = Integer.parseInt(floorsTextField.getText());
            people = Integer.parseInt(peopleTextField.getText());

            st = (Stage) scenePane.getScene().getWindow();
            st.close();

            Group group = new Group();

            startX = 80;
            startY = 40;
            endX = startX;
            endY = startY + floors * floorHeight - (floorHeight - liftHeight);

            Line l1 = new Line(startX, startY, endX, endY);
            l1.setStrokeWidth(10);
            l1.setStroke(Color.DARKGREY);
            group.getChildren().add(l1);

            for (int i = 0; i < floors; i++) {
                Text curFloor = new Text(8, startY + floorHeight * i + floorHeight / 2, "Stage: " + Integer.toString(floors - i));
                curFloor.setFill(Color.DARKGREY);
                curFloor.setStyle("-fx-font-size: 16; -fx-text-alignment: center");
                group.getChildren().add(curFloor);
            }

            startX += 40;
            endX = startX;
            for (int i = 0; i < elevators; i++) {
                Rectangle curLift = new Rectangle(startX + width * i, endY - liftHeight, liftWidth, liftHeight);
                curLift.setStyle("-fx-stroke: black; -fx-stroke-width: 3;");
                lifts.add(curLift);
                group.getChildren().add(curLift);
            }

            for (int i = 0; i < elevators; i++) {
                Text curCounter = new Text(lifts.get(i).getX() + 11, lifts.get(i).getY() + liftHeight / 2 + 10, "0");
                curCounter.setFill(Color.BLUEVIOLET);
                curCounter.setStyle("-fx-font-size: 26; -fx-text-alignment: center");
                curCounter.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
                counter.add(curCounter);
                group.getChildren().add(curCounter);
            }


            stage = new Stage();
            stage.setTitle("Elevators-Simulating");

            Scene scene = new Scene(group);
            stage.setScene(scene);
            stage.setWidth(elevators * width + startX);
            stage.setHeight(endY + startY + 50);

            stage.show();

            ctrl = new ElevatorController(floors, capacity, elevators);
            house = new Building(people, floors, ctrl);
            Thread threadCtrl = new Thread(ctrl);
            Thread threadHouse = new Thread(house);
            threadHouse.start();
            threadCtrl.start();

            new AnimationTimer() {
                long last = 0;
                @Override
                public void handle(long now) {
                    int step;
                    if ((now - last) / 1000 > 1_500_000) {
                        for (int i = 0; i < elevators; i++) {
                            step = floorHeight;
                            step *= -1 * ctrl.getElevators().get(i).getDirection();

                            TranslateTransition moveLift = new TranslateTransition();
                            TranslateTransition moveText = new TranslateTransition();

                            moveLift.setNode(lifts.get(i));
                            counter.get(i).setText(Integer.toString(ctrl.getElevators().get(i).getPassengers().size()));
                            moveText.setNode(counter.get(i));

                            moveLift.setDuration(Duration.millis(1000));
                            moveText.setDuration(Duration.millis(1000));

                            moveLift.setByY(step);
                            moveText.setByY(step);

                            moveLift.play();
                            moveText.play();
                        }
                        last = now;
                    }
                }
            }.start();

            //loop();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
