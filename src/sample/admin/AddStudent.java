package sample.admin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


import static sample.Main.serv;


public class AddStudent {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboBoxGroup;

    @FXML
    private TextField pibField;

    @FXML
    private Button addStudentButton;

    @FXML
    void initialize() {
        serv.setAllGroupSelect(comboBoxGroup);

        addStudentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(comboBoxGroup.getValue() != null && pibField.getText() != "") {
                    String[] arr = comboBoxGroup.getValue().split("\\Q|\\E");
                    int idGroup = Integer.parseInt(arr[1].trim());
                    serv.addStudent(idGroup, pibField.getText());
                    pibField.setText("");
                }
            }
        });

    }
}
