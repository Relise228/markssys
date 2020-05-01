package sample;


import info.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class ServerAction {
    public int idTeacher;

    byte serverAddress[] = {127, 0, 0, 1,};
    int serverPort = 6788;
    int buffersize = 7000;


    public boolean logIn(String log, String pass) {

        String msg = "login@" + log.trim() + "|" + pass.trim();
        System.out.println(msg);
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();

                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress); // створення объекту за IP-адресою

                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);        //надсилає пакет
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

                aSocket.receive(reply);

                String repl = new String(reply.getData());

                aSocket.close();

                if(repl.trim().equals("1")) {
                    idTeacher = Integer.parseInt(repl.trim());
                    System.out.println(repl.trim());
                    return true;
                }
                break;
            } while (msg.trim().equals("quit"));

            // помилка при створення socket
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());

            // помилка при отриманні
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    return false;
    }

    public void getGroups(TableColumn cellGropups, TableView groupTabel) {
        String msg = "allGroups@";

        // аргументи - повідомлення і адреса сервера
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();

                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress); // створення объекту за IP-адресою

                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);        //надсилає пакет
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

            // помилка при створення socket
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());

            // помилка при отриманні
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }

    public void getCourses(TableColumn cellCourses, TableView coursesTable) {
        String msg = "allCourses@";

        // аргументи - повідомлення і адреса сервера
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();

                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress); // створення объекту за IP-адресою

                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);        //надсилає пакет
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

                aSocket.receive(reply);

                String repl = new String(reply.getData()).trim();

                String[] ph = repl.split("#");


                ObservableList<CoursesClass> data = FXCollections.observableArrayList();

                for (String str : ph) {
                    String[] group = str.split("\\Q|\\E");
                    for (String dsad : ph) {
                        System.out.println(dsad);
                    }
                    System.out.println("-----------------------------------");
                    data.add(new CoursesClass(group[1].trim(), Integer.parseInt(group[0].trim())));

                }

                cellCourses.setCellValueFactory(new PropertyValueFactory<>("courses"));

                coursesTable.setItems(data);


                aSocket.close();


            } while (msg.trim().equals("quit"));

            // помилка при створення socket
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());

            // помилка при отриманні
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }

    public void getCoursesByGroup(int idGroup, TableColumn cellCourses, TableView coursesTable) {
        String msg = "getCoursesByGroup@" + idGroup;

        // аргументи - повідомлення і адреса сервера
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();

                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress); // створення объекту за IP-адресою

                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);        //надсилає пакет
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

                aSocket.receive(reply);

                String repl = new String(reply.getData()).trim();

                String[] ph = repl.split("#");


                ObservableList<CoursesClass> data = FXCollections.observableArrayList();

                for (String str : ph) {
                    String[] group = str.split("\\Q|\\E");
                    for (String dsad : ph) {
                        System.out.println(dsad);
                    }
                    System.out.println("-----------------------------------");
                    data.add(new CoursesClass(group[1].trim(), Integer.parseInt(group[0].trim())));

                }

                cellCourses.setCellValueFactory(new PropertyValueFactory<>("courses"));

                coursesTable.setItems(data);


                aSocket.close();


            } while (msg.trim().equals("quit"));

            // помилка при створення socket
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());

            // помилка при отриманні
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

                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress); // створення объекту за IP-адресою

                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);        //надсилає пакет
                byte[] buffer = new byte[buffersize];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

                aSocket.receive(reply);

                String repl = new String(reply.getData()).trim();

                String[] ph = repl.split("#");


                ObservableList<StudentNameClass> data = FXCollections.observableArrayList();

                for (String str : ph) {
                    System.out.println(str);
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

            // помилка при створення socket
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());

            // помилка при отриманні
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }

    public void getGroupsByCourse(int idCourse, TableColumn cellGroups, TableView groupTabel) {
        String msg = "getGroupsByCourse@" + idCourse;

        // аргументи - повідомлення і адреса сервера
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();

                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress); // створення объекту за IP-адресою

                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);        //надсилає пакет
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

            // помилка при створення socket
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());

            // помилка при отриманні
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

                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress); // створення объекту за IP-адресою

                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);        //надсилає пакет
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
                    data.add(new GroupsAllInfoClass( Integer.parseInt(group[0].trim()),
                            group[1].trim(), Integer.parseInt(group[2].trim()),
                            Integer.parseInt(group[3].trim()),
                            Integer.parseInt(group[4].trim())));
                }
                String[] identColumn = {"nameGroup", "year", "studentCount", "avgMark"};
                String[] nameColumn = {"Группа", "Рік", "К-сть студентів", "Рейт. бал"};

                for(int i = 0; i <= countColumns - 2; i++){
                    TableColumn tableColumn = new TableColumn<>(nameColumn[i]);
                    tableColumn.setCellValueFactory(new PropertyValueFactory<>(identColumn[i]));
                    resultTable.getColumns().add(tableColumn);
                }




//                resultColumn.setText("Student Name");
//                resultColumn.setMinWidth(494);
//                resultColumn.setCellValueFactory(new PropertyValueFactory<>("nameGroup"));
//
                resultTable.setItems(data);
                resultTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

                aSocket.close();

            } while (msg.trim().equals("quit"));

            // помилка при створення socket
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());

            // помилка при отриманні
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }

    public void getStudentsFullInfo(int idGroup, int idCourse, TableView resultTable) {
        String msg = "getStudentsFullInfo@" + idGroup + "|" + idCourse;
        resultTable.getColumns().clear();
        try {
            do {
                DatagramSocket aSocket = new DatagramSocket();
                byte[] msgByte = msg.getBytes();

                InetAddress serverInetAddress = InetAddress.getByAddress(serverAddress); // створення объекту за IP-адресою

                DatagramPacket request = new DatagramPacket(msgByte, msg.length(), serverInetAddress, serverPort);
                aSocket.send(request);        //надсилає пакет
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
                    data.add(new StudentsFullInfoClass(Integer.parseInt(student[0].trim()),
                            student[1].trim(),
                            Integer.parseInt(student[2].trim())));
                }
                String[] identColumn = {"nameStudent", "markStudent"};
                String[] nameColumn = {"ПІБ", "Оцінка"};

                for(int i = 0; i <= countColumns - 2; i++){
                    TableColumn tableColumn = new TableColumn<>(nameColumn[i]);
                    tableColumn.setCellValueFactory(new PropertyValueFactory<>(identColumn[i]));
                    resultTable.getColumns().add(tableColumn);
                }


                resultTable.setItems(data);
                resultTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

                aSocket.close();

            } while (msg.trim().equals("quit"));

            // помилка при створення socket
        } catch (
                SocketException e) {
            System.out.println("(Client) Socket: " + e.getMessage());

            // помилка при отриманні
        } catch (
                IOException e) {
            System.out.println("(Client) IO: " + e.getMessage());
        }
    }




}
