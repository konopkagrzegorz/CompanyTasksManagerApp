package company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterModel {

    private Connection connection;

    public RegisterModel() {
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

    public boolean isRegister (String username, String password) throws SQLException {
        boolean status = false;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String salt = BCrypt.gensalt(12);
        password = BCrypt.hashpw(password,salt);

        String sqlRegisterQuery = "SELECT * FROM " + dbConnection.TABLE_USERS + " WHERE " + dbConnection.COLUMN_USERNAME +  " = ?";
        String sqlRegisterInsert = "INSERT INTO " + dbConnection.TABLE_USERS + " (" + dbConnection.COLUMN_USERNAME + "," + dbConnection.COLUMN_PASSWORD + ") VALUES (?,?)";

        try {
            pr = this.connection.prepareStatement(sqlRegisterQuery);
            pr.setString(1,username);
            rs = pr.executeQuery();

            if (rs.next() == false ) {
                try {
                    pr = this.connection.prepareStatement(sqlRegisterInsert);
                    pr.setString(1,username);
                    pr.setString(2,password);
                    if(pr.executeUpdate() == 1) {
                        status = true;
                    }
                } catch (SQLException exInside) {
                    exInside.getMessage();
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        } finally {
            pr.close();
            rs.close();
        }
        return status;
    }
}
