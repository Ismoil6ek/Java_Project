package studentUser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mainFunctions.Users;

import java.io.IOException;

public class firstMenu extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("firstMenu.fxml"));

        stage.setScene(new Scene(root, 300, 275));
        Users u = (Users) stage.getUserData();
        System.out.println(u.getName());stage.setTitle("First Menu");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
