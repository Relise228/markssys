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

public class AddGroup {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField yearField;

    @FXML
    private Button addGroupButton;

    @FXML
    private TextField groupNameField;

    @FXML
    void initialize() {
        yearField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    yearField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        addGroupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(yearField.getText().length() == 4 && !yearField.getText().equals("") && !groupNameField.getText().equals("")) {
                    serv.addGroup(groupNameField.getText(), yearField.getText());
                    System.out.println("Group added");
                    groupNameField.setText("");
                    yearField.setText("");
                }
            }
        });

    }
}
