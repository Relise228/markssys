package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import static sample.Main.serv;

public class ReportPanel {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button generateButton;

    @FXML
    private ComboBox<String> groupComboBox;

    @FXML
    private ComboBox<String> courseComboBox;

    @FXML
    private CheckBox marks60ChechBox;

    @FXML
    void initialize() {
        serv.setAllGroupSelect(groupComboBox);

        groupComboBox.valueProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(groupComboBox.getValue() != null) {
                    int idGroup = Integer.parseInt(groupComboBox.getValue().split("\\Q|\\E")[1].trim());
                    serv.setCourseSelect(idGroup, courseComboBox);
                }
            }
        });

        generateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(groupComboBox.getValue() != null && courseComboBox.getValue() != null) {
                    int idGroup = Integer.parseInt(groupComboBox.getValue().split("\\Q|\\E")[1].trim());
                    int idCourse = Integer.parseInt(courseComboBox.getValue().split("\\Q|\\E")[1].trim());
                    String groupName = groupComboBox.getValue().split("\\Q|\\E")[0].trim();
                    String courseName = courseComboBox.getValue().split("\\Q|\\E")[0].trim();
                   if (marks60ChechBox.isSelected()) {
                       serv.getStudentMarks60(idGroup, idCourse, groupName, courseName);
                   } else {
                       serv.getStudentMarks(idGroup, idCourse, groupName, courseName);
                   }
                   groupComboBox.setValue(null);
                   courseComboBox.setValue(null);
                   marks60ChechBox.setSelected(false);
                }
            }
        });

    }
}
