package App;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Login loginWindow;

    @Override
    public void start(Stage primaryStage) throws IOException {

        loginWindow = new Login();
        loginWindow.show(primaryStage);

    }


    public static void main(String[] args) {
        launch(args);
    }
}