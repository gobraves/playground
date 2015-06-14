package Todo.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by simple_chen on 15-6-14.
 */
public class PlanCell implements Initializable{

    @FXML
    Label listLabel = new Label("(empty)");
    @FXML
    Button okButton = new Button("(>)");
    @FXML
    Button deleteButton = new Button("(>)");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PlanCell.class.getResource("view/plan.fxml"));
            HBox planCell = (HBox)loader.load();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }
}

