package Todo.view;

import Todo.MainApp;
import Todo.model.Plan;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.util.Callback;


import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Created by simple_chen on 15-6-10.
 */
public class Controller implements Initializable {
    @FXML
    ListView<Plan> ingList;
    @FXML
    ListView<Plan> completedList;
    @FXML
    TitledPane ingTitledPane;
    @FXML
    TitledPane completedTitledPane;
    @FXML
    TextArea contentField;
    @FXML
    DatePicker dateField;
    @FXML
    TextField titleEditField;
    @FXML
    Button addButton;
    @FXML
    Button updateButton;

    Button okButton;
    Button deleteButton;

    private MainApp mainApp;

    @FXML
    private void handleAddButton() {
                titleEditField.setText(null);
                dateField.setValue(LocalDate.now());
                contentField.setText(null);
    }

    @FXML
    private void handleUpdateButton() {
        try {
            Plan plan = new Plan();
            plan.setDate(dateField.getValue());
            plan.setcontent(contentField.getText());
            plan.setState(false);
            plan.setTitle(titleEditField.getText());

            mainApp.getData().add(plan);
            ingList.setItems(mainApp.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ingTitledPane = new TitledPane("正在进行", ingList);
        completedTitledPane = new TitledPane("已完成", completedList);

          ingList.setCellFactory(new Callback<ListView<Plan>, ListCell<Plan>>() {
              @Override
              public ListCell<Plan> call(ListView<Plan> param) {
                  return new PlanListCell();
              }
          });

          ingList.getSelectionModel().selectedItemProperty().addListener((e, oldValue, newValue) -> {
                   showPlanDetail(newValue);
          });
    }

    public void showPlanDetail(Plan plan) {
        titleEditField.setText(plan.getTitle());
        contentField.setText(plan.getcontent());
        dateField.setValue(plan.getDate());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        ingList.setItems(mainApp.getData());

    }

    public class PlanListCell extends ListCell<Plan> {
        HBox hbox = new HBox();
        Label label = new Label("(empty)");
        Pane pane = new Pane();
        Button okButton = new Button("ok");
        Button deleteButton = new Button("x");
        //Plan lastItem;
        Plan lastItem;


        public PlanListCell() {
            super();
            hbox.getChildren().addAll(label, pane,okButton,deleteButton);
            HBox.setHgrow(pane, Priority.ALWAYS);
        }

        public void updateItem(Plan item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);
            if (empty) {
              lastItem = null;
                setGraphic(null);
            } else {
                lastItem = item;
                label.setText(lastItem != null ? lastItem.getTitle() : "<null>");
                setGraphic(hbox);
            }
        }

        public void setDeleteButton() {
            okButton.setOnAction(e -> {
                ingList.getItems().remove(this);
            });
        }
    }
}




