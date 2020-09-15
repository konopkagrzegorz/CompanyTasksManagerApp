package company;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

    private Connection connection;

    public LoginModel() {
        try {
            this.connection = dbConnection.getConnecion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } if (this.connection == null) {
            System.exit(1);
        }
    }

    public boolean isDatabaseConnected(){
        return this.connection != null;
    }


    public boolean loginTo(String username, String password) throws SQLException {
        boolean status = false;
        PreparedStatement pr = null;
        ResultSet rs = null;

        String sqlLoginQuery = "SELECT " + dbConnection.COLUMN_PASSWORD + " FROM " + dbConnection.TABLE_USERS + " WHERE " + dbConnection.COLUMN_USERNAME +  " = ?";

        try {
            pr = this.connection.prepareStatement(sqlLoginQuery);
            pr.setString(1,username);
            rs = pr.executeQuery();

            if (rs.next()) {
                status = BCrypt.checkpw(password,rs.getString(1));
                if (status) {
                    return true;
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong password!");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Username " + username + " doesn't exist!");
                alert.showAndWait();
            }
            return false;
        } catch (SQLException ex) {
            ex.getMessage();
        } finally {
            pr.close();
            rs.close();
        }
        return false;
    }
}
