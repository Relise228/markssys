package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class TeacherPanel {

    @FXML
    private Button reportButton;

    @FXML
    private Label teacherName;

    @FXML
    private Button castButton;

    @FXML
    private Button exitButton;

    @FXML
    private TableView resultTable;

    @FXML
    private TableView<GroupsClass> groupTable;

    @FXML
    private TableView courseTable;

    @FXML
    private TableColumn course;

    @FXML
    private TableColumn<GroupsClass, String> group;

    @FXML
    void initialize() {
        ServerAction serv = new ServerAction();

        serv.getGroups(group, groupTable);
        serv.getCourses(course, courseTable);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) exitButton.getScene().getWindow();
                stage.close();

                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("../fxml/sample.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                stage = new Stage();
                stage.setScene(new Scene(root, 350, 305));
                stage.setTitle("Log In");
                stage.show();
            }
        });

    }
}
