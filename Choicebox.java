/**
 * Created by simple_chen on 15-5-30.
 */

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.Observable;

public class Choicebox extends Application {

    Stage stage;
    Scene scene;
    Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("choiceBox");
        button = new Button("click me");

        //add item to choiceBox
        final ChoiceBox<String> choiceBox = new ChoiceBox<String>();
        choiceBox.getItems().addAll("Algorithm","Machine Learning","Data Mining"
                ,"Computer Network","Javascript","Python");

        //set default value
        choiceBox.setValue("Algorithm");

        button.setOnAction(e -> getChoice(choiceBox));

        //add listener
        ChangeListener v = new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                System.out.println(newValue + "? are you sure?");
            }
        };
        choiceBox.getSelectionModel().selectedItemProperty().addListener(v);

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20,20,20,20));
        vbox.getChildren().addAll(choiceBox,button);

        scene = new Scene(vbox,400,200);
        stage.setScene(scene);
        stage.show();
    }

    public void getChoice(ChoiceBox<String> choiceBox) {
        String result = choiceBox.getValue();
        System.out.println("Yes,I want to learn "+result);
    }
}
