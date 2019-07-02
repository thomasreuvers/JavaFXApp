package App;

import App.Handlers.Authentication;
import App.Modals.DatabaseModal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PanelController implements Initializable {

    private Authentication auth = new Authentication();
    private ObservableList<DatabaseModal> list = FXCollections.observableArrayList();

    @FXML
    private TableView<DatabaseModal> tableView;
    @FXML
    private TableColumn<DatabaseModal, String> dbName;
    @FXML
    private TableColumn<DatabaseModal, String> servAddress;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dbName.setCellValueFactory(new PropertyValueFactory<>("DatabaseName"));
        servAddress.setCellValueFactory(new PropertyValueFactory<>("ServerURL"));


        list.addAll(auth.fetchDatabases());

        tableView.setItems(list);
    }

    @FXML
    private void testAction(ActionEvent event) throws IOException
    {
        System.out.println(tableView.getItems().get(tableView.getSelectionModel().getFocusedIndex()).getUsername());//TODO: PRINT OUT SELECTED MODAL'S NAME! Do same for server adress.
    }
    @FXML
    private void dropCurrentDatabase(ActionEvent event) throws IOException
    {
        auth.dropDatabase(tableView.getItems().get(tableView.getSelectionModel().getFocusedIndex()).getServerURL(), tableView.getItems().get(tableView.getSelectionModel().getFocusedIndex()).getDatabaseName());
        list.clear();
        list.addAll(auth.fetchDatabases());
    }


    @FXML
    private void onMenuItemClick(ActionEvent event) throws IOException
    {
        Panel mainPanel = new Panel();
        mainPanel.createInputDialog();
        list.clear();
        list.addAll(auth.fetchDatabases());
    }
}
