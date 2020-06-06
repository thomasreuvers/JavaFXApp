package App;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {


    /*
    * show();
    * Shows the login window on the users screen.
     */

    public void show(Stage primaryStage) throws IOException
    {
        Parent loginWindow = FXMLLoader.load(getClass().getResource("../Layouts/loginWindow.fxml"));

        primaryStage.setTitle("Desktop TMS");
        primaryStage.setScene(new Scene(loginWindow, 500, 550));
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

}
