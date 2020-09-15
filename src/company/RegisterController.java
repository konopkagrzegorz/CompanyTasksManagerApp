package company;

import company.RegisterModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {


    private RegisterModel registerModel = new RegisterModel();

    @FXML
    private Label dbStatusRegister;

    @FXML
    private TextField usernameRegisterField;

    @FXML
    private PasswordField pwRegisterField;

    @FXML
    private PasswordField pwConfirmRegisterField;

    @FXML
    private Button submitButton;

    @FXML
    private Button clearRegisterButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (this.registerModel.isDatabaseConnected()) {
            this.dbStatusRegister.setText("CONNECTED TO DATABASE");
        } else {
            this.dbStatusRegister.setText("NOT CONNECTED TO DATABASE");
        }
    }

    @FXML
    public void clearRegisterData(MouseEvent event) {

        String username = usernameRegisterField.getText().trim();
        String password = pwRegisterField.getText().trim();
        String confirmPassword = pwConfirmRegisterField.getText().trim();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Clear message box");
            alert.setHeaderText(null);
            alert.setContentText("Username and/or password is empty...");
            alert.showAndWait();
        } else {
            usernameRegisterField.clear();
            pwRegisterField.clear();
            pwConfirmRegisterField.clear();
        }
    }

    @FXML
    public void submitRegister(ActionEvent event) throws SQLException {
        String username = usernameRegisterField.getText().trim();
        String password = pwRegisterField.getText().trim();
        String confirmedPassword = pwConfirmRegisterField.getText().trim();
        if (!username.isEmpty() && (password.equals(confirmedPassword)) && !password.isEmpty()) {
            if (registerModel.isRegister(username, password)) {
                try {
                    Parent loginWindowParent = FXMLLoader.load(getClass().getResource("view/login.fxml"));
                    Scene loginWindowScene = new Scene(loginWindowParent);
                    Stage register = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    register.setScene(loginWindowScene);
                    register.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Username already exist!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Passwords doesn't match!");
            alert.showAndWait();
        }
    }
}