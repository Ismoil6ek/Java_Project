package students;

import Books.Books;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainFunctions.Users;
import mainFunctions.UsersRepository;
import studentUser.FirstMenuController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private ObservableList<Students> students;
    @FXML
    public TextField txtLogin;
    @FXML
    public TextField txtPassword;
    @FXML
    public TextField txtRoles;
    @FXML
    public TextField txtStatus;
    @FXML
    public TextField txtFine;
    @FXML
    public TableView tblStudents;
    @FXML
    public TextField txtFirstname;
    @FXML
    public TextField txtLastname;
    @FXML
    public TextField txtStudentID;

    @FXML
    public void overdueStudent(ObservableList<Students> st){
        this.students = st;

    }
    @FXML
    public void addStudent() throws SQLException {
        Students student = new Students(
                "",
                txtFirstname.getText(),
                txtLastname.getText(),
                txtStudentID.getText(),
                txtLogin.getText(),
                txtPassword.getText(),
                txtRoles.getText(),
                "0",
                txtFine.getText()
        );
        try {
            student.setId(StudentsRepository.getInstance().add(student));
            this.students.add(student);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        Users users = new Users(
                "",
                txtLogin.getText(),
                txtFirstname.getText(),
                txtRoles.getText(),
                txtPassword.getText()
        );
        users.setId(UsersRepository.getInstance().add(users));
    }

    @FXML
    public void deleteStudent() {
        try {
            Students student = (Students) tblStudents.getSelectionModel().getSelectedItem();
            StudentsRepository.getInstance().delete(student.getId());
            UsersRepository.getInstance().deleteName(student.getLogin());
            this.students.remove(student);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
    @FXML
    public void editStudent() {
        try{
            Students student = (Students) tblStudents.getSelectionModel().getSelectedItem();
            Users user = UsersRepository.getInstance().findByName(student.getLogin());
            this.students.remove(student);
            //users update
            UsersRepository.getInstance().update(txtFirstname.getText(), user.getLogin(), txtLogin.getText(), txtPassword.getText(), txtRoles.getText());
            //Students update
            StudentsRepository.getInstance().update(student.getId(), txtFirstname.getText(), txtLastname.getText(), txtStudentID.getText(),txtLogin.getText(),
                    txtPassword.getText(),txtRoles.getText(),"0",txtFine.getText());
            student.setFirstName(txtFirstname.getText());
            student.setLastName(txtLastname.getText());
            student.setStudentID(txtStudentID.getText());
            student.setLogin(txtLogin.getText());
            student.setPassword(txtPassword.getText());
            student.setRole(txtRoles.getText());
            student.setStatus(txtStatus.getText());
            student.setFine(txtFine.getText());
            this.students.add(student);
            tblStudents.refresh();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
    @FXML
    public void selectRow() throws SQLException {
        tblStudents.refresh();
        Students student = (Students) tblStudents.getSelectionModel().getSelectedItem();
        Students curStudent = StudentsRepository.getInstance().get(student.getId());
        this.txtFirstname.setText(curStudent.getFirstName());
        this.txtLastname.setText(curStudent.getLastName());
        this.txtStudentID.setText(curStudent.getStudentID());
        this.txtLogin.setText(curStudent.getLogin());
        this.txtPassword.setText(curStudent.getPassword());
        this.txtRoles.setText(curStudent.getRole());
//        this.txtStatus.setText(curStudent.getStatus());
        this.txtFine.setText(curStudent.getFine());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.students = StudentsRepository.getInstance().getAll();
            this.tblStudents.setItems(students);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        tblStudents.setOnMouseClicked(e->{
            try {
                selectRow();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        });
    }
    @FXML
    public void viewStudent(ActionEvent e) throws IOException, SQLException {
        Students student = (Students) tblStudents.getSelectionModel().getSelectedItem();
        Students curStudent = StudentsRepository.getInstance().get(student.getId());
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root1 = loader.load(getClass().getResource("viewStudent.fxml").openStream());
        stage.setScene(new Scene(root1));
        ((Node)e.getSource()).getScene().getWindow().hide();
        viewController viewController = loader.getController();
        viewController.setData(curStudent);
        stage.show();
        stage.setOnCloseRequest(eve -> ((Stage)((Node)e.getSource()).getScene().getWindow()).show());

    }
}
