package company;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private LoginModel loginModel = new LoginModel();

    @FXML
    private Label dbStatusLogin;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField pwField;

    @FXML
    private Button loginButton;

    @FXML
    private Button clearLoginButton;

    @FXML
    private Button registerButton;

    @FXML
    private AnchorPane registerWindow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (this.loginModel.isDatabaseConnected()) {
            this.dbStatusLogin.setText("CONNECTED TO DATABASE");
        } else {
            this.dbStatusLogin.setText("NOT CONNECTED TO DATABASE");
        }
    }

    @FXML
    public void clearLoginData(MouseEvent event) {

        String username = usernameField.getText().trim();
        String password = pwField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Clear message box");
            alert.setHeaderText(null);
            alert.setContentText("username and/or password is empty...");

            alert.showAndWait();
        } else {
            usernameField.clear();
            pwField.clear();
        }
    }

    @FXML
    void submitLogin(MouseEvent event) {
        String username = usernameField.getText().trim();
        String password = pwField.getText().trim();
        if (!username.isEmpty() && !password.isEmpty()) {
            try {
                if (loginModel.loginTo(username, password)) {
                    Stage stage = (Stage) this.loginButton.getScene().getWindow();
                    stage.close();
                    if (username.equals("adominiuk") || username.equals("gkonopka")) {
                        adminWindow();
                    } else {
                        userWindow();
                    }
                }
            } catch (Exception localExeption) {
                System.out.println(localExeption.getMessage());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Username already exist!");
            alert.showAndWait();
        }
    }


    @FXML
    public void openRegisterWindow(MouseEvent event) throws IOException {
        Parent registerWindowParent = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene registerWindowScene = new Scene(registerWindowParent);
        Stage register = (Stage) ((Node) event.getSource()).getScene().getWindow();

        register.setScene(registerWindowScene);
        register.show();
    }

    public void userWindow() {
        try {
            Stage userstage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("userwindow.fxml").openStream());
            UserWindowController userWindowController = (UserWindowController)loader.getController();
            userstage.getIcons().add(new Image("file:E:\\Program%20Files\\JavaTimBuchalka\\KECDataApp\\src\\company_logo.png"));
            Scene scene = new Scene(root,960,600);
            userstage.setScene(scene);
            userstage.setTitle("COMPANY TASKS VIEWER");
            userstage.setResizable(false);
            userstage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void adminWindow() {
        try {
            Stage adminstage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("adminwindow.fxml").openStream());
            AdminWindowController adminWindowController = (AdminWindowController) loader.getController();
            adminstage.getIcons().add(new Image("file:E:\\Program%20Files\\JavaTimBuchalka\\KECDataApp\\src\\company_logo.png"));
            Scene scene = new Scene(root, 960, 600);
            adminstage.setScene(scene);
            adminstage.setTitle("COMPANY TASKS VIEWER");
            adminstage.setResizable(false);
            adminstage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
