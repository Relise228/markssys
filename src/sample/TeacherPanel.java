package sample;

import java.io.IOException;

import info.CoursesClass;
import info.GroupsClass;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
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
    private TableColumn resultColumn;

    @FXML
    private TableView<GroupsClass> groupTable;

    @FXML
    private TableView courseTable;

    @FXML
    private TableColumn course;

    @FXML
    private TableColumn<GroupsClass, String> group;

    private int selectedIdGroup = 0;
    private int selectedIdCourse = 0;

    @FXML
    void initialize() {
        groupTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        courseTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        ServerAction serv = new ServerAction();

        serv.getGroups(group, groupTable);
        serv.getCourses(course, courseTable);

        castButton. setOnAction(actionEvent -> {
            selectedIdGroup = 0;
            selectedIdCourse = 0;
            resultTable.getColumns().clear();
            serv.getGroups(group, groupTable);
            serv.getCourses(course, courseTable);
        });

        exitButton.setOnAction(new EventHandler<>() {
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

        groupTable.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observableValue, oldValue, newValue) -> {

            if (groupTable.getSelectionModel().getSelectedItem() != null) {
                TableView.TableViewSelectionModel selectionModel = groupTable.getSelectionModel();
                ObservableList selectedRow = selectionModel.getSelectedItems();
                GroupsClass selectedItem = (GroupsClass) selectedRow.get(0);

                selectedIdGroup = selectedItem.getId();

                if (selectedIdCourse == 0 ) {
                    serv.getCoursesByGroup(selectedIdGroup, course, courseTable);
                    serv.getStudentNamesByGroup(selectedIdGroup, resultTable);
                } else if (selectedIdGroup != 0) {
                    serv.getStudentsFullInfo(selectedIdGroup, selectedIdCourse, resultTable);
                }

            }
        });


        courseTable.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observableValue, oldValue, newValue) -> {

            if (courseTable.getSelectionModel().getSelectedItem() != null) {
                TableView.TableViewSelectionModel selectionModel = courseTable.getSelectionModel();
                ObservableList selectedRow = selectionModel.getSelectedItems();
                CoursesClass selectedItem = (CoursesClass) selectedRow.get(0);

                selectedIdCourse = selectedItem.getIdCourse();

                if (selectedIdGroup == 0 ) {
                    serv.getGroupsByCourse(selectedIdCourse, group, groupTable);
                    serv.getGroupsFullInfo(selectedIdCourse, resultTable);
                } else if (selectedIdCourse != 0) {
                    serv.getStudentsFullInfo(selectedIdGroup, selectedIdCourse, resultTable);
                }


            }
        });

    }
}
