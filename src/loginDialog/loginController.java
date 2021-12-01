package loginDialog;

import students.Students;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.stage.Stage;
import librarians.Librarians;
import librarians.LibrariansRepository;
import mainFunctions.*;
import studentUser.FirstMenuController;
import students.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @FXML
    public Label lblUsername;
    @FXML
    public Label lblPassword;
    @FXML
    public TextField txtUsername;
    @FXML
    public PasswordField fldPassword;
    @FXML
    public RadioButton rbOption1;
    @FXML
    public RadioButton rbOption2;
    @FXML
    public RadioButton rbOption3;
    @FXML
    public Button btnLogin;

    @FXML
    public void handleLogin(ActionEvent e) throws SQLException, IOException {

        Users users = UsersRepository.getInstance().findByName(txtUsername.getText());
        if(users == null){
            System.out.println("Not found");

        } else{
            if(users.getPassword().equals(fldPassword.getText())){
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Parent root1 = loader.load(getClass().getResource("/studentUser/firstMenu.fxml").openStream());
                stage.setTitle(users.getRole());
                stage.setScene(new Scene(root1));

                FirstMenuController firstMenuController = loader.getController();
                firstMenuController.setData(users);
                ((Node)e.getSource()).getScene().getWindow().hide();
                stage.show();
                stage.setOnCloseRequest(eve -> ((Stage)((Node)e.getSource()).getScene().getWindow()).show());

                System.out.println(users.getLogin());
            }


        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}