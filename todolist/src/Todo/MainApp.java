package Todo;

import Todo.model.Plan;
import Todo.view.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by simple_chen on 15-5-28.
 */
public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane root;

    private ObservableList<Plan> data = FXCollections.observableArrayList();

    public void MainApp() {
        data.add(new Plan("asdf","dsaf"));
    }


    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("To Do");

        initRoot();

        showEdit();
    }
      
    
    public void initRoot() {
        try {
            //load main from main fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/root.fxml"));
            root = (BorderPane) loader.load();

            //set showmain into the root pane
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEdit() {
        try {
            //load fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/edit.fxml"));
            AnchorPane edit = (AnchorPane) loader.load();

            //connect controller
            Controller controller = loader.getController();
            controller.setMainApp(this);

            root.setCenter(edit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public ObservableList<Plan> getData() {
        return data;
    }

}
