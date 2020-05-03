package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    protected static byte serverAddress[] = {127, 0, 0, 1,};
    protected static int serverPort = 6788;
    protected static int buffersize = 7000;
    public static ServerAction serv = new ServerAction();


    @Override
    public void start(Stage primaryStage) throws Exception{
        String addr = "127.0.0.1";

        int i = 0;
        String byteStr = "";
        for (int j = 0; j < addr.length(); j++) {
            if (addr.charAt(j) != '.') byteStr = byteStr + addr.charAt(j);
            else {
                serverAddress[i] = (byte) (Integer.parseInt(byteStr));
                i++;
                byteStr = "";
            }
        }
        serverAddress[i] = (byte) (Integer.parseInt(byteStr));
        for (i = 0; i < 4; i++) System.out.print(serverAddress[i] + ".");
        System.out.println();


        Parent root = FXMLLoader.load(getClass().getResource("../fxml/sample.fxml"));
        primaryStage.setTitle("Log In");
        primaryStage.setScene(new Scene(root, 350, 305));
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
