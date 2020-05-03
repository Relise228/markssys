package sample.admin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static sample.Main.serv;

public class addTeacher {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField pibField;

    @FXML
    private Button addTeacherButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void initialize() {
        addTeacherButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!loginField.getText().equals("") && !passwordField.getText().equals("") && !pibField.getText().equals("")) {
                    System.out.println("Login - " + loginField.getText()
                    + " Password - " + passwordField.getText() + " PIB - " + pibField.getText());
                    serv.addTeacher(pibField.getText(), loginField.getText(), passwordField.getText());
                        loginField.setText("");
                        passwordField.setText("");
                        pibField.setText("");
                }
            }
        });

    }
}
