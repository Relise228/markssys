package sample;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class ServerAction {

    byte serverAddress[] = {127, 0, 0, 1,};
    int serverPort = 6789;
    int buffersize = 7000;


    public void logIn(String log, String pass) {

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


                System.out.println("ok" + repl.trim());


                aSocket.close();
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
    }


}
