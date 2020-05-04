package sample;


import info.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import static sample.Main.serverAddress;
import static sample.Main.serverPort;
import static sample.Main.buffersize;


public class ServerAction {

   private int selectedIdStudent = 0;
   private int idTeacher = 0;
   private boolean admin = false;

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    boolean logIn(String log, String pass) {
        String msg = "login@" + log.trim() + "|" + pass.trim();
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();
                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress);
                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                String repl = new String(reply.getData()).trim();
                String[] str = repl.split("\\Q|\\E");
                aSocket.close();

                if(str.length == 2) {
                    this.idTeacher = Integer.parseInt(str[0].trim());
                    if (str[1].equals("Admin")) {
                        this.admin = true;
                    }
                    return true;
                }
                break;
            } while (msg.trim().equals("quit"));
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    return false;
    }

    public void getGroups(TableColumn cellGropups, TableView groupTabel) {
        String msg = "allGroups@";
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();
                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress);
                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                String repl = new String(reply.getData()).trim();
                String[] ph = repl.split("#");

                ObservableList<GroupsClass> data = FXCollections.observableArrayList();

                for (String str : ph) {
                    String[] group = str.split("\\Q|\\E");
                    data.add(new GroupsClass( group[1].trim(), Integer.parseInt(group[0].trim())));
                }

                cellGropups.setCellValueFactory(new PropertyValueFactory<>("groups"));

                groupTabel.setItems(data);
                aSocket.close();
            } while (msg.trim().equals("quit"));
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }

    public void getCourses(TableColumn cellCourses, TableView coursesTable) {
        String msg = "allCourses@";
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();
                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress);
                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                String repl = new String(reply.getData()).trim();
                String[] ph = repl.split("#");

                ObservableList<CoursesClass> data = FXCollections.observableArrayList();

                for (String str : ph) {
                    String[] group = str.split("\\Q|\\E");
                    data.add(new CoursesClass(group[1].trim(), Integer.parseInt(group[0].trim())));
                }

                cellCourses.setCellValueFactory(new PropertyValueFactory<>("courses"));

                coursesTable.setItems(data);
                aSocket.close();
            } while (msg.trim().equals("quit"));
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }

    public void getCoursesByGroup(int idGroup, TableColumn cellCourses, TableView coursesTable) {
        String msg = "getCoursesByGroup@" + idGroup;
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();
                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress);
                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                String repl = new String(reply.getData()).trim();
                String[] ph = repl.split("#");
                ObservableList<CoursesClass> data = FXCollections.observableArrayList();
                for (String str : ph) {
                    String[] group = str.split("\\Q|\\E");
                    data.add(new CoursesClass(group[1].trim(), Integer.parseInt(group[0].trim())));
                }
                cellCourses.setCellValueFactory(new PropertyValueFactory<>("courses"));
                coursesTable.setItems(data);
                aSocket.close();
            } while (msg.trim().equals("quit"));
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }

    public void getStudentNamesByGroup(int idGroup,  TableView resultTable) {
        resultTable.getColumns().clear();
        String msg = "getStudentNamesByGroup@" + idGroup;
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();
                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress);
                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                String repl = new String(reply.getData()).trim();
                String[] ph = repl.split("#");

                ObservableList<StudentNameClass> data = FXCollections.observableArrayList();

                for (String str : ph) {
                    data.add(new StudentNameClass(str.trim()));
                }

                TableColumn tableColumn = new TableColumn<>("Student Name");
                tableColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
                resultTable.getColumns().add(tableColumn);
                tableColumn.setMinWidth(494);

                resultTable.setItems(data);
                resultTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                aSocket.close();
            } while (msg.trim().equals("quit"));
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }

    public void getGroupsByCourse(int idCourse, TableColumn cellGroups, TableView groupTabel) {
        String msg = "getGroupsByCourse@" + idCourse;
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();
                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress);
                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                String repl = new String(reply.getData()).trim();
                String[] ph = repl.split("#");

                ObservableList<GroupsClass> data = FXCollections.observableArrayList();

                for (String str : ph) {
                    String[] group = str.split("\\Q|\\E");
                    data.add(new GroupsClass(group[1].trim(), Integer.parseInt(group[0].trim())));

                }

                cellGroups.setCellValueFactory(new PropertyValueFactory<>("groups"));

                groupTabel.setItems(data);
                aSocket.close();
            } while (msg.trim().equals("quit"));
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }

    public void  getGroupsFullInfo(int idCourse, TableView resultTable) {
        String msg = "getGroupsFullInfo@" + idCourse;
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();
                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress);
                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                String repl = new String(reply.getData()).trim();
                String[] ph = repl.split("#");
                ObservableList<GroupsAllInfoClass> data = FXCollections.observableArrayList();
                int countColumns = 0;
                for (String str : ph) {
                    String[] group = str.split("\\Q|\\E");
                    countColumns = group.length;
                    try {
                        data.add(new GroupsAllInfoClass( Integer.parseInt(group[0].trim()),
                                group[1].trim(), Integer.parseInt(group[2].trim()),
                                Integer.parseInt(group[3].trim()),
                                Integer.parseInt(group[4].trim())));
                    } catch (NumberFormatException e) {

                    }
                }
                String[] identColumn = {"nameGroup", "year", "studentCount", "avgMark"};
                String[] nameColumn = {"Группа", "Рік", "К-сть студентів", "Рейт. бал"};

                for(int i = 0; i <= countColumns - 2; i++){
                    TableColumn tableColumn = new TableColumn<>(nameColumn[i]);
                    tableColumn.setCellValueFactory(new PropertyValueFactory<>(identColumn[i]));
                    resultTable.getColumns().add(tableColumn);
                }
                resultTable.setItems(data);
                resultTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                aSocket.close();
            } while (msg.trim().equals("quit"));
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }

    public void getStudentsFullInfo(int idGroup, int idCourse, TableView resultTable) {
        String msg = "getStudentsFullInfo@" + idGroup + "|" + idCourse;
        resultTable.getColumns().clear();
        resultTable.setEditable(true);
        resultTable.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observableValue, oldValue, newValue) -> {
            if (resultTable.getSelectionModel().getSelectedItem() != null) {
                TableView.TableViewSelectionModel selectionModel = resultTable.getSelectionModel();
                ObservableList selectedRow = selectionModel.getSelectedItems();
                StudentsFullInfoClass selectedItem = (StudentsFullInfoClass) selectedRow.get(0);

                selectedIdStudent = selectedItem.getIdStudent();
            }
        });
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();
                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress);
                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                String repl = new String(reply.getData()).trim();
                String[] ph = repl.split("#");
                ObservableList<StudentsFullInfoClass> data = FXCollections.observableArrayList();
                int countColumns = 0;
                for (String str : ph) {
                    String[] student = str.split("\\Q|\\E");
                    countColumns = student.length;
                    try {
                        data.add(new StudentsFullInfoClass(Integer.parseInt(student[0].trim()),
                                student[1].trim(),
                                Integer.parseInt(student[2].trim())));
                    } catch (NumberFormatException e) {
                    }
                }
                String[] identColumn = {"nameStudent", "markStudent"};
                String[] nameColumn = {"ПІБ", "Оцінка"};

                for(int i = 0; i <= countColumns - 2; i++){
                    TableColumn tableColumn = new TableColumn<>(nameColumn[i]);
                    if(tableColumn.getText() == "Оцінка" && this.checkTeacherCourseReading(idGroup, idCourse, this.idTeacher)) {
                       tableColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

                        tableColumn.setOnEditCommit(
                                new EventHandler<TableColumn.CellEditEvent<StudentsFullInfoClass, Integer>>() {
                                    @Override
                                    public void handle(TableColumn.CellEditEvent<StudentsFullInfoClass, Integer> t) {
                                        setMark(idCourse, selectedIdStudent, t.getNewValue());
                                        getStudentsFullInfo(idGroup, idCourse, resultTable);
                                    }
                                }
                        );
                    }
                    tableColumn.setCellValueFactory(new PropertyValueFactory<>(identColumn[i]));
                    resultTable.getColumns().add(tableColumn);
                }
                resultTable.setItems(data);
                resultTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                aSocket.close();
            } while (msg.trim().equals("quit"));

        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }

    private void setMark(int idCourse, int idStudent, int mark) {
        String msg = "updateStudentMark@" + idStudent + "|" + idCourse + "|" + mark;
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();
                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress);
                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                aSocket.close();
            } while (msg.trim().equals("quit"));
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }

    private boolean checkTeacherCourseReading(int idGroup, int idCourse, int idTeacher) {
        String msg = "checkTeacherCourseReading@" + idGroup + "|" + idCourse + "|" + idTeacher;
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();
                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress);
                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                String repl = new String(reply.getData()).trim();
                aSocket.close();
                if(Integer.parseInt(repl.trim()) == 1) return true;
            } while (msg.trim().equals("quit"));

        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
        return false;
    }

    boolean checkAdmin() {
        return this.admin;
    }

    public void setAllGroupSelect(ComboBox groupSelect) {
        String msg = "allGroups@";
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();
                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress);
                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                String repl = new String(reply.getData()).trim();
                String[] ph = repl.split("#");

                ObservableList data = FXCollections.observableArrayList();

                for (String str : ph) {
                    String[] group = str.split("\\Q|\\E");
                    data.add(group[1].trim() + " | " + group[0].trim());
                }

                groupSelect.setItems(data);
                aSocket.close();
            } while (msg.trim().equals("quit"));
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }

    public void addStudent(int idGroup, String pib) {
        String msg = "addStudent@" + idGroup + "|" + pib;
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();
                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress);
                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                aSocket.close();
            } while (msg.trim().equals("quit"));
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }

    public void addTeacher(String pib, String login, String password) {
        String msg = "addTeacher@" + pib + "|" + login + "|" + password;
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();
                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress);
                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                aSocket.close();
            } while (msg.trim().equals("quit"));
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }

    public void addGroup(String nameGroup, String year) {
        String msg = "addGroup@" + nameGroup + "|" + year;
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();
                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress);
                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                aSocket.close();
            } while (msg.trim().equals("quit"));
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }

    public void addCourse(String nameCourse, String hours){
        String msg = "addCourse@" + nameCourse + "|" + hours;
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();
                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress);
                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                aSocket.close();
            } while (msg.trim().equals("quit"));
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }

    public void notAddedCourses(int idGroup, ComboBox courseSelect) {
        String msg = "notAddedCourses@" + idGroup;
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();
                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress);
                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                String repl = new String(reply.getData()).trim();
                String[] ph = repl.split("#");

                ObservableList data = FXCollections.observableArrayList();

                for (String str : ph) {
                    String[] course = str.split("\\Q|\\E");
                    data.add(course[1].trim() + " | " + course[0].trim());
                }

                courseSelect.setItems(data);

                aSocket.close();
            } while (msg.trim().equals("quit"));
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }

    public void getAllTeachers(ComboBox teachersCombo) {
        String msg = "allTeachers@";
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();
                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress);
                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                String repl = new String(reply.getData()).trim();
                String[] ph = repl.split("#");

                ObservableList data = FXCollections.observableArrayList();

                for (String str : ph) {
                    String[] course = str.split("\\Q|\\E");
                    data.add(course[1].trim() + " | " + course[0].trim());
                }
                teachersCombo.setItems(data);
                aSocket.close();
            } while (msg.trim().equals("quit"));
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }

    public void addGroupOnCourse(int idGroup, int idCourse, int idTeacher) {
        String msg = "addGroupOnCourse@" + idGroup + "|" + idCourse + "|" + idTeacher;
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();
                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress);
                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                aSocket.close();
            } while (msg.trim().equals("quit"));
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }

    void setCourseSelect(int idGroup, ComboBox courseCombo) {
        String msg = "getCoursesByGroup@" + idGroup;
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();
                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress);
                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                String repl = new String(reply.getData()).trim();
                String[] ph = repl.split("#");

                ObservableList data = FXCollections.observableArrayList();

                for (String str : ph) {
                    String[] course = str.split("\\Q|\\E");
                    data.add(course[1].trim() + " | " + course[0].trim());
                }

                courseCombo.setItems(data);

                aSocket.close();
            } while (msg.trim().equals("quit"));
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }

    void getStudentMarks(int idGroup, int idCourse, String groupName, String courseName) {
        String msg = "getStudentMarks@" + idGroup + "|" + idCourse;
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();
                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress);
                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                String repl = new String(reply.getData()).trim();
                String[] ph = repl.split("#");

                DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
                Date date = new Date();
                String name = "report_" + groupName + "_" + courseName + "_" + dateFormat.format(date);

                FileWriter fw = new FileWriter(new File("reports" ,"_" + name + ".txt"));
                fw.write("Група - " + groupName + "  Курс - " + courseName + "\n\n\n");
                for (String str : ph) {
                    fw.write(str.trim() + "\n");
                }
                fw.close();
                aSocket.close();
            } while (msg.trim().equals("quit"));
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }

    void getStudentMarks60(int idGroup, int idCourse, String groupName, String courseName) {
        String msg = "getStudentMarks60@" + idGroup + "|" + idCourse;
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();
                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress);
                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                String repl = new String(reply.getData()).trim();
                String[] ph = repl.split("#");

                DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
                Date date = new Date();
                String name = "report_" + groupName + "_" + courseName + "_" + dateFormat.format(date);

                FileWriter fw = new FileWriter(new File("reports" ,"_" + name + ".txt"));
                fw.write("Група - " + groupName + "  Курс - " + courseName + "\n\n\n");
                for (String str : ph) {
                    fw.write(str.trim() + "\n");
                }
                fw.close();
                aSocket.close();
            } while (msg.trim().equals("quit"));
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }





}
