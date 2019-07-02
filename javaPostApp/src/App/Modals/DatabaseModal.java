package App.Modals;

import javafx.beans.property.SimpleStringProperty;

public class DatabaseModal {

    private SimpleStringProperty serverURL;
    private SimpleStringProperty databaseName;
    private SimpleStringProperty username;
    private SimpleStringProperty password;

    public DatabaseModal(String serverURL, String databaseName, String username, String password)
    {
        this.serverURL = new SimpleStringProperty(serverURL);
        this.databaseName = new SimpleStringProperty(databaseName);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
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

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}


