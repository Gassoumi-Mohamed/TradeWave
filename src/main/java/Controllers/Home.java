package Controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Home extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Authentification.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void UserAcc_Load_interface(ActionEvent actionEvent) {
    }

    public void Set_Dachboard(ActionEvent actionEvent) {
    }
}
