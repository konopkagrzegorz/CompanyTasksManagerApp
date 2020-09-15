package company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("COMPANY TASKS VIEWER");
        primaryStage.setScene(new Scene(root, 600, 420));
        primaryStage.getIcons().add(new Image("file:\\E:\\Program%20Files\\JavaFX\\CompanyTaskManagerApp\\src\\company_logo.png"));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}