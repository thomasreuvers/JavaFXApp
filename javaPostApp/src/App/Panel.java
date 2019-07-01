package App;

import App.Handlers.Authentication;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Optional;

public class Panel {


    public void show(Stage primaryStage) throws IOException
    {
        Parent panelWindow = FXMLLoader.load(getClass().getResource("../Layouts/panelWindow.fxml"));

        primaryStage.setTitle("Project Post");
        primaryStage.setScene(new Scene(panelWindow, 1000, 600));
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void createInputDialog()
    {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Add Database");
        dialog.setHeaderText("Fill in all the fields to add your database");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField serverUrl = new TextField();
        serverUrl.setPromptText("Server Address");

        TextField databaseName = new TextField();
        databaseName.setPromptText("Database Name");

        TextField username = new TextField();
        username.setPromptText("Username");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Server Address:"), 0, 0);
        grid.add(serverUrl, 1, 0);

        grid.add(new Label("Database Name:"), 0, 1);
        grid.add(databaseName, 1, 1);

        grid.add(new Label("Username:"), 0, 2);
        grid.add(username, 1, 2);

        grid.add(new Label("Password:"), 0, 3);
        grid.add(password, 1, 3);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        Authentication auth = new Authentication(); // Call add new database function and give the user entered data with.
        auth.addNewDatabase(serverUrl.getText(), databaseName.getText(), username.getText(), password.getText());
    }

}
