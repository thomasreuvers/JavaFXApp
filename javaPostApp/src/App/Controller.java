package App;

import App.Handlers.Authentication;
import App.Modals.DatabaseModal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/*
* Controller class
* Handles all of the actions.
 */

public class Controller implements Initializable {

    private Authentication auth = new Authentication();

    @FXML
    private Button loginBtn;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField passWord;


    @FXML
    private void loginUser(ActionEvent event) throws IOException
    {
        if (!passWord.getText().equals("") || !userName.getText().equals(""))
        {
            if (auth.login(userName.getText(), passWord.getText())) // If method returns true user credentials were valid.
            {

                Panel mainPanel = new Panel();

                Stage primaryStage = (Stage) loginBtn.getScene().getWindow();

                mainPanel.show(primaryStage);

            }
        }else
        {
            auth.fieldsAreNull(); //Fields are empty and popup will be shown.
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
