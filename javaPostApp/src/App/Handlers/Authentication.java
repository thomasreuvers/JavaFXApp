package App.Handlers;

import App.Controller;
import App.Modals.DatabaseModal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Authentication {

    private String userKey;

    public boolean login(String username, String password)//TODO: Check for more secure dataype for password
    {
        boolean verification = false;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1/post_app?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET","root","password123");

            String sql = "SELECT COUNT(*) FROM users WHERE username = ? and password = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username); // replace first ? with value for username
            stmt.setString(2, password);    // replace second ? with value for password

            ResultSet rs = stmt.executeQuery(); //Execute query

            while(rs.next()) //While fetching data
            {
                if(rs.getBoolean(1)) //If user credentials match a row.
                {
                   verification = true; //If row with username and password has been found.

                    sql = "SELECT userKey FROM users WHERE username = ? and password = ?";
                    stmt = con.prepareStatement(sql);
                    stmt.setString(1, username);
                    stmt.setString(2, password);

                    rs= stmt.executeQuery();

                    while(rs.next())
                    {
                        userKey += rs.getString(1); //Set userKey as current logged in users key. //TODO: GET userKey from database userkey is always null
                    }

                } else{
                    invalidCredentials(); //User credentials do not exist in database error will be called.
                }
            }

            con.close(); //Close connection

        }catch(Exception e)
        {
            System.out.println(e);
            connectionNull(); //Error connecting to server a connection error will be called.
        }

        return verification;
    }

    public void addNewDatabase(String serverURL, String databaseName, String username, String password)
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://"+serverURL+"/"+databaseName+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET",username,password);

            String sql = "CREATE TABLE IF NOT EXISTS posts (" +
                    "project_id INT AUTO_INCREMENT," +
                    "title VARCHAR(255)," +
                    "description VARCHAR(255)," +
                    "image VARCHAR(255)," +
                    "links VARCHAR(255)," +
                    "publishDate VARCHAR(255)," +
                    "PRIMARY KEY (project_id))";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.execute();

            con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1/post_app?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET","root","password123");

            sql = "INSERT INTO data_bases (serveraddress, database_name, username, password, userKey) VALUES (?, ?, ? ,?, ?)"; //TODO: ENCRYPT PASSWORDS!
            stmt = con.prepareStatement(sql);
            stmt.setString(1, serverURL);
            stmt.setString(2, databaseName);
            stmt.setString(3, username);
            stmt.setString(4, password);
            stmt.setString(5, userKey);

            stmt.execute();

            con.close(); //Close connection

        }catch(Exception e)
        {
            System.out.println(e);
            connectionNull(); //Error connecting to server a connection error will be called.
        }
    }

    public List<DatabaseModal> fetchDatabases()
    {
        List<DatabaseModal> databaseModalList = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1/post_app?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET","root","password123");

            String sql = "SELECT serveraddress, database_name FROM data_bases WHERE userKey = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, userKey); // replace first ? with value for userKey

            ResultSet rs = stmt.executeQuery(); //Execute query

            while(rs.next())
            {
                DatabaseModal db = new DatabaseModal(rs.getString(1), rs.getString(2)); // New instance of DatabaseModal class which takes database data
                databaseModalList.add(db); // Add the instance to a DatabaseModal list;
            }

            con.close();

        }catch (Exception e)
        {
            e.printStackTrace();
            connectionNull();
        }


        return databaseModalList;

    }

/*
* Error popups
 */
    public void invalidCredentials()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "User credentials are invalid!", ButtonType.OK);
        alert.setTitle("Wrong Credentials!");
        alert.showAndWait();
    }
    public void fieldsAreNull()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields", ButtonType.OK);
        alert.setTitle("Empty Fields!");
        alert.showAndWait();
    }
    public void connectionNull() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Could not connect to the server", ButtonType.OK);
        alert.setTitle("Connection Error!");
        alert.showAndWait();
    }

/*
* Getters & Setters
 */

}


