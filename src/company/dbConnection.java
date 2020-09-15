package company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    private final static String DB_NAME = "kecdata.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:E:\\Program Files\\JavaFX\\CompanyTasksManagerApp\\" + DB_NAME;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    public static final String TABLE_GPN = "gpn";
    public static final String COLUMN_GLOBAL_PROJECT_NUMBER = "Global_Project_Number";
    public static final String COLUMN_GLOBAL_PROJECT_NAME = "Global_Project_Name";
    public static final String COLUMN_GLOBAL_PROJECT_STATUS = "Status";

    public static final String TABLE_PHASE = "phase";
    public static final String COLUMN_PHASE_GLOBAL_PROJECT_NUMBER = "Global_Project_Number";
    public static final String COLUMN_PHASE_NUMBER = "Phase_Number";
    public static final String COLUMN_PHASE_NAME = "Phase_Name";
    public static final String COLUMN_PHASE_STAGE = "Phase_Stage";
    public static final String COLUMN_PHASE_STATUS = "Phase_Status";

    public static final String TABLE_SUB_PROJECT = "sub_project";
    public static final String COLUMN_SUB_PROJECT_PHASE_NUMBER = "Phase_Number";
    public static final String COLUMN_SUB_PROJECT_PROJECT_NUMBER = "Sub_Project_Number";
    public static final String COLUMN_SUB_PROJECT_PROJECT_NAME = "Sub_Project_Name";
    public static final String COLUMN_SUB_PROJECT_PROJECT_MANAGER = "Project_Manager";
    public static final String COLUMN_SUB_PROJECT_STATUS = "Status";

    public static final String TABLE_PROJECT_MANAGERS = "project_managers";
    public static final String COLUMN_PROJECT_MANAGERS_PROJECT_MANAGER = "Project_Manager";

    public static Connection getConnecion() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(CONNECTION_STRING);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
