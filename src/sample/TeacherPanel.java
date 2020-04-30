package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    private TableView groupTable;

    @FXML
    private TableView courseTable;

    @FXML
    private TableColumn course;

    @FXML
    private TableColumn group;

    @FXML
    void initialize() {

    }
}
