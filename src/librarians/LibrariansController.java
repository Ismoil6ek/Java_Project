package librarians;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import mainFunctions.Users;
import mainFunctions.UsersRepository;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LibrariansController implements Initializable {
    @FXML
    public TableView tblLibrarians;
    private ObservableList<Librarians> librarians;
    @FXML
    public TextField txtName;
    @FXML
    public TextField txtLogin;
    @FXML
    public TextField txtPassword;
    @FXML
    public TextField txtRole;

    @FXML
    public void addLibrarian() throws SQLException {
        Librarians librarian = new Librarians(
                "",
                txtName.getText(),
                txtLogin.getText(),
                txtPassword.getText(),
                txtRole.getText()
        );
        try {
            librarian.setId(LibrariansRepository.getInstance().add(librarian));
            this.librarians.add(librarian);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        Users users = new Users(
                "",
                txtLogin.getText(),
                txtName.getText(),
                txtRole.getText(),
                txtPassword.getText()
        );
        users.setId(UsersRepository.getInstance().add(users));
    }

    @FXML
    public void deleteLibrarian() {
        try {
            Librarians librarian = (Librarians) tblLibrarians.getSelectionModel().getSelectedItem();
            LibrariansRepository.getInstance().delete(librarian.getId());
            UsersRepository.getInstance().deleteName(librarian.getLogin());
            this.librarians.remove(librarian);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @FXML
    public void editLibrarian() {
        try{
            Librarians librarian = (Librarians) tblLibrarians.getSelectionModel().getSelectedItem();
            Users user = (Users) UsersRepository.getInstance().findByName(librarian.getLogin());
            this.librarians.remove(librarian);
            UsersRepository.getInstance().update(txtName.getText(), librarian.getLogin(), txtLogin.getText(), txtPassword.getText(), txtRole.getText());

            LibrariansRepository.getInstance().update(librarian.getId(), txtName.getText(), txtLogin.getText(), txtPassword.getText(),txtRole.getText());

            librarian.setName(txtName.getText());
            librarian.setLogin(txtLogin.getText());
            librarian.setPassword(txtPassword.getText());
            librarian.setRole(txtRole.getText());
            this.librarians.add(librarian);
            this.tblLibrarians.setItems(librarians);
            tblLibrarians.refresh();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @FXML
    public void selectRow() throws SQLException {
        tblLibrarians.refresh();
        Librarians librarian = (Librarians) tblLibrarians.getSelectionModel().getSelectedItem();
        Librarians curLibrarian = LibrariansRepository.getInstance().get(librarian.getId());
        this.txtName.setText(curLibrarian.getName());
        this.txtLogin.setText(curLibrarian.getLogin());
        this.txtPassword.setText(curLibrarian.getPassword());
        this.txtRole.setText(curLibrarian.getRole());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.librarians = LibrariansRepository.getInstance().getAll();
            this.tblLibrarians.setItems(librarians);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        tblLibrarians.setOnMouseClicked(e->{
            try {
                selectRow();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
