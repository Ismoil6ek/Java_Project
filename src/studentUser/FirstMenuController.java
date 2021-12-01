package studentUser;

import Books.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import mainFunctions.Users;
import students.MainController;
import students.Students;
import students.StudentsRepository;
import students.viewController;

public class FirstMenuController implements Initializable {
    @FXML
    public Button btnBrrBooks;
    @FXML
    public Button btnMonthReport;
    @FXML
    public Button btnLogout;
    @FXML
    public Button btnAllLibrarian;
    @FXML
    public Button btnBooks;
    @FXML
    public Button btnAboutMe;
    @FXML
    public Button btnOverDue;
    @FXML
    public Button btnStudents;
    @FXML
    private Users user;
    @FXML
    public void setData(Users user){

        this.user = user;
        System.out.println(user.getRole());
        btnLogout.setOnAction(e -> ((Button)e.getSource()).getScene().getWindow().hide());
//        btnAboutMe.setVisible(false);
        switch (user.getRole()) {
            case "admin" -> {
                btnMonthReport.setVisible(false);
                btnOverDue.setVisible(false);
            }
            case "student" -> {
                btnStudents.setVisible(false);
                btnAllLibrarian.setVisible(false);
                btnBooks.setVisible(true);
                btnAboutMe.setVisible(true);

            }
            case "librarian" -> {
                btnAllLibrarian.setVisible(false);
                btnOverDue.setVisible(true);

            }
            default -> throw new IllegalStateException("Unexpected value: " + user.getRole());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void AllBook(ActionEvent e) throws IOException, SQLException {
        ((Node)e.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root1 = loader.load(getClass().getResource("/Books/books.fxml").openStream());
        stage.setScene(new Scene(root1));
        stage.setOnCloseRequest(eve -> ((Stage)((Node)e.getSource()).getScene().getWindow()).show());
        stage.setTitle("ALL books");
        Controller controller = loader.getController();
        controller.setUser(user);
        stage.show();

    }
    @FXML
    public void AllStudents(ActionEvent e) throws IOException {
        ((Node)e.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root1 = loader.load(getClass().getResource("/students/students.fxml").openStream());
        stage.setScene(new Scene(root1));
        stage.setTitle("All students");
        stage.setOnCloseRequest(eve -> ((Stage)((Node)e.getSource()).getScene().getWindow()).show());
//        Controller controller = loader.getController();
        stage.show();
    }
    @FXML
    public void AllLibrarians(ActionEvent e) throws IOException {
        ((Node)e.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root1 = loader.load(getClass().getResource("/librarians/librarians.fxml").openStream());
        stage.setScene(new Scene(root1));
        stage.setOnCloseRequest(eve -> ((Stage)((Node)e.getSource()).getScene().getWindow()).show());
        stage.setTitle("All librarians");
        stage.show();
    }
    @FXML
    public void AboutMe(ActionEvent e) throws IOException, SQLException {

        Students curStudent = (Students) StudentsRepository.getInstance().getByName(this.user.getName());
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root1 = loader.load(getClass().getResource("/students/viewStudent.fxml").openStream());
        stage.setScene(new Scene(root1));
        ((Node)e.getSource()).getScene().getWindow().hide();
        viewController viewController = loader.getController();
        viewController.setData(curStudent);
        stage.setTitle("About " + curStudent.getFirstName() + "  " + curStudent.getLastName());
        stage.show();
        stage.setOnCloseRequest(eve -> ((Stage)((Node)e.getSource()).getScene().getWindow()).show());
    }
    @FXML
    public void overdue(ActionEvent e) throws IOException, SQLException {
        ((Node)e.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root1 = loader.load(getClass().getResource("/students/students.fxml").openStream());
        stage.setScene(new Scene(root1));
        stage.setTitle("All students");
        stage.setOnCloseRequest(eve -> ((Stage)((Node)e.getSource()).getScene().getWindow()).show());
        MainController controller = loader.getController();
        controller.overdueStudent(StudentsRepository.getInstance().getAllDue());
        stage.show();
    }
}
