package students;

import Books.Books;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import mainFunctions.Users;
import mainFunctions.UsersRepository;
import students.*;
import Books.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class viewController implements Initializable {

    @FXML
    public Label lblFirstname;
    @FXML
    public Label lblLastname;
    @FXML
    public Label lblStudentID;
    @FXML
    public Label lblLogin;
    @FXML
    public Label lblPassword;
    @FXML
    public Label lblRoles;
    @FXML
    public Label lblStatus;
    @FXML
    public Label lblFine;
    @FXML
    public Button btnBlockStudent;
    @FXML
    public TableView tblAddresses;
    @FXML
    public TableView tblBooks;

    Students curStudent;
    @FXML
    public void setData(Students student) throws SQLException {
        curStudent = student;
        lblFirstname.setText(student.getFirstName());
        lblLastname.setText(student.getLastName());
        lblStudentID.setText(student.getStudentID());
        lblLogin.setText(student.getLogin());
        lblPassword.setText(student.getPassword());

            if (student.getStatus().equals("1")){
                btnBlockStudent.setText("Unblock");
                lblStatus.setText("Blocked");
            }
            else
                lblStatus.setText("Unblocked");
            lblRoles.setText(student.getRole());
            lblFine.setText(student.getFine());
        ObservableList<Books> blist = BooksRepository.getInstance().getBook(student.getId());
            this.tblBooks.setItems(blist);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
    @FXML
    public void blockStudent(ActionEvent actionEvent) throws SQLException {
//       Books book = (Books) tblBooks.getSelectionModel().getSelectedItem();
        System.out.println(curStudent.getStatus());
        if (curStudent.getStatus().equals("0")){
            StudentsRepository.getInstance().block(curStudent.getId());
            curStudent.setStatus("1");
            lblStatus.setText("Blocked");
            btnBlockStudent.setText("Unblock");

        } else {
            StudentsRepository.getInstance().unblock(curStudent.getId());
            curStudent.setStatus("0");
            lblStatus.setText("Unblocked");
            btnBlockStudent.setText("Block");

        }

    }
}
