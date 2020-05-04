package sample.admin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static sample.Main.serv;

public class AddCourse {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField hoursField;

    @FXML
    private Button addCourseButton;

    @FXML
    private TextField courseNameField;

    @FXML
    void initialize() {
        hoursField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    hoursField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        addCourseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!hoursField.getText().equals("") && !courseNameField.getText().equals("")) {
                    serv.addCourse(courseNameField.getText(), hoursField.getText());
                    courseNameField.setText("");
                    hoursField.setText("");
                }
            }
        });

    }
}
