package App.Modals;

import javafx.beans.property.SimpleStringProperty;

public class DatabaseModal {

    private SimpleStringProperty serverURL;
    private SimpleStringProperty databaseName;

    public DatabaseModal(String serverURL, String databaseName)
    {
        this.serverURL = new SimpleStringProperty(serverURL);
        this.databaseName = new SimpleStringProperty(databaseName);
    }

    public String getServerURL() {
        return serverURL.get();
    }

    public void setServerURL(String serverURL) {
        this.serverURL = new SimpleStringProperty(serverURL);
    }

    public String getDatabaseName() {
        return databaseName.get();
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = new SimpleStringProperty(databaseName);
    }
}
