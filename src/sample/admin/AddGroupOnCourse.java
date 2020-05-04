package sample.admin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import static sample.Main.serv;

public class AddGroupOnCourse {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addGroupOnCourseButton;

    @FXML
    private ComboBox<String> groupComboBox;

    @FXML
    private ComboBox<String> courseComboBox;

    @FXML
    private ComboBox<String> teacherComboBox;

    @FXML
    void initialize() {
        serv.setAllGroupSelect(groupComboBox);
        serv.getAllTeachers(teacherComboBox);

        groupComboBox.valueProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(groupComboBox.getValue() != null) {
                    System.out.println(groupComboBox.getValue());
                    int idGroup = Integer.parseInt(groupComboBox.getValue().split("\\Q|\\E")[1].trim());
                    serv.notAddedCourses(idGroup, courseComboBox);
                }
            }
        });

        addGroupOnCourseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(groupComboBox.getValue() != null && courseComboBox.getValue() != null && teacherComboBox.getValue() != null) {
                    int idGroup = Integer.parseInt(groupComboBox.getValue().split("\\Q|\\E")[1].trim());
                    int idCourse = Integer.parseInt(courseComboBox.getValue().split("\\Q|\\E")[1].trim());
                    int idTeacher = Integer.parseInt(teacherComboBox.getValue().split("\\Q|\\E")[1].trim());
                    serv.addGroupOnCourse(idGroup, idCourse, idTeacher);
                    groupComboBox.setValue(null);
                    courseComboBox.setValue(null);
                    teacherComboBox.setValue(null);

                }
            }
        });

    }
}
