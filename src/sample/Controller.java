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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static sample.Main.serv;

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

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if( serv.logIn(usrNameField.getText(), passwordField.getText())) {
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();
                    Parent root = null;
                    if (serv.checkAdmin()) {
                        try {
                            root = FXMLLoader.load(getClass().getResource("../fxml/admin/admin_panel.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        stage = new Stage();
                        stage.setScene(new Scene(root, 750, 534));
                        stage.setTitle("Admin Panel");
                        stage.show();
                    } else {
                        try {
                            root = FXMLLoader.load(getClass().getResource("../fxml/teacher_panel.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        stage = new Stage();
                        stage.setScene(new Scene(root, 750, 534));
                        stage.setTitle("Teacher Panel");
                        stage.show();
                    }

                }
            }
        });

    }
}
