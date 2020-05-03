package sample.admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import info.CoursesClass;
import info.GroupsClass;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static sample.Main.serv;

public class AdminPanel {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button castButton;

    @FXML
    private Button reportButton;

    @FXML
    private Button exitButton;

    @FXML
    private TableView<?> resultTable;

    @FXML
    private TableView<?> groupTable;

    @FXML
    private TableColumn<?, ?> group;

    @FXML
    private TableView<?> courseTable;

    @FXML
    private TableColumn<?, ?> course;

    @FXML
    private Button addStudentButton;

    @FXML
    private Button addGroupButton;

    @FXML
    private Button addGroupOnCourseButton;

    @FXML
    private Button addTeacherButton;

    private int selectedIdGroup = 0;
    private int selectedIdCourse = 0;

    @FXML
    void initialize() {
        groupTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        courseTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


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
                serv.setAdmin(false);
                Stage stage = (Stage) exitButton.getScene().getWindow();
                stage.close();

                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("../../fxml/sample.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                stage = new Stage();
                stage.setScene(new Scene(root, 350, 305));
                stage.setTitle("Log In");
                stage.show();
            }
        });

        addStudentButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("../../fxml/admin/addStudents.fxml"));
                try {
                    Loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = Loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Add Student");
                stage.show();
            }
        });

        addTeacherButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("../../fxml/admin/addTeachers.fxml"));
                try {
                    Loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = Loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Add Teacher");
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

