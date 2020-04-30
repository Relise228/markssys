package sample;


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
    int serverPort = 6789;
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
                    data.add(new GroupsClass( group[1].trim()));
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
                    data.add(new CoursesClass(group[1].trim()));

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

}
