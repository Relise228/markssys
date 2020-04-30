package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;

    @FXML
    private TextField usrNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void initialize() {

        ServerAction serv = new ServerAction();

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                serv.logIn(usrNameField.getText(), passwordField.getText());
            }
        });

    }
}
