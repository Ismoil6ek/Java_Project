package Books;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import mainFunctions.Users;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class Controller implements Initializable {


    private Users currentUser;
    private ObservableList<Books> books;
    @FXML
    public Button btnDelete;
    @FXML
    public Button btnView;
    @FXML
    public Button btnAdd;
    @FXML
    public HBox hb;
    @FXML
    public CheckBox checkBorrowed;
    @FXML
    public TableView tblAddresses;
    @FXML
    public TextField txtTitle;
    @FXML
    public TextField txtAuthor;
    @FXML
    public TextField txtSubject;
    @FXML
    public TextField txtISBN;
    @FXML
    public TextField txtDate;
    @FXML
    public Button btnEdit;
    @FXML
    public TextField txtStudent;
    @FXML
    public void addBook(){
        Books book = new Books(
                "",
                txtTitle.getText(),
                txtAuthor.getText(),
                txtSubject.getText(),
                txtISBN.getText(),
                txtDate.getText(),
                txtStudent.getText(),
                "0"
                );
        try {

            book.setId(BooksRepository.getInstance().add(book));
            this.books.add(book);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
    @FXML
    public void deleteBook(){
        try {
            Books book = (Books) tblAddresses.getSelectionModel().getSelectedItem();
            BooksRepository.getInstance().delete(book.getId());
            this.books.remove(book);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }
    @FXML
    public void editBook() {
        try{
            Books book = (Books) tblAddresses.getSelectionModel().getSelectedItem();
            this.books.remove(book);
            BooksRepository.getInstance().update(book.getId(), txtTitle.getText(), txtISBN.getText(), txtSubject.getText(), txtAuthor.getText(), txtDate.getText(), txtStudent.getText());
            book.setAuthor(txtAuthor.getText());
            book.setIsbn(txtISBN.getText());
            book.setPublishedDate(txtDate.getText());
            book.setSubject(txtSubject.getText());
            book.setTitle(txtTitle.getText());
            book.setStudent(txtStudent.getText());
            this.books.add(book);
            tblAddresses.refresh();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
    @FXML
    public void selectRow() throws SQLException {
        tblAddresses.refresh();
        Books book = (Books) tblAddresses.getSelectionModel().getSelectedItem();
        Books curBook = BooksRepository.getInstance().get(book.getId());
        this.txtTitle.setText(curBook.getTitle());
        this.txtAuthor.setText(curBook.getAuthor());
        this.txtDate.setText(curBook.getPublishedDate());
        this.txtISBN.setText(curBook.getIsbn());
        this.txtSubject.setText(curBook.getSubject());
        this.txtStudent.setText(curBook.getStudent());
    }
    @FXML
    public void setUser(Users user) throws SQLException {
        currentUser = user;

        System.out.println("Role: " + user.getRole());
        if(!currentUser.getRole().equals("student")) {
            btnAdd.setVisible(true);
            btnDelete.setVisible(true);
            btnView.setVisible(true);
            btnEdit.setVisible(true);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            this.books = BooksRepository.getInstance().getAll();
            this.tblAddresses.setItems(books);
            tblAddresses.setOnMouseClicked(e->{
                try {
                    selectRow();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            });
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
    @FXML
    public void viewBook(ActionEvent actionEvent) throws IOException {
        Books book = (Books) tblAddresses.getSelectionModel().getSelectedItem();
        try {
            Books curBook = BooksRepository.getInstance().get(book.getId());
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root1 = loader.load(getClass().getResource("viewBook.fxml").openStream());
            stage.setScene(new Scene(root1));
            viewController viewController = loader.getController();
            viewController.setData(curBook);
            stage.show();
            stage.setOnCloseRequest(eve -> ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).show());
        } catch (SQLException throwable) {
            System.out.println("Invalid cell clicked" + throwable.getMessage());
        }

        ((Node)actionEvent.getSource()).getScene().getWindow().hide();


    }
    @FXML
    public void filter(ActionEvent e) throws SQLException {
        String textTitle="", textSubject="", textAuthor="", textISBN="", textDate="", textStudent="", textBorrowed;
        if(txtTitle != null) {
            textTitle = txtTitle.getText();
        }if(txtDate != null) {
            textDate = txtDate.getText();
        }
        if(txtISBN != null) {
            textISBN = txtISBN.getText();
        }
        if(txtSubject != null) {
            textSubject = txtSubject.getText();
        }
        if(txtAuthor != null) {
            textAuthor = txtAuthor.getText();
        }
        if(txtStudent != null) {
            textStudent = txtStudent.getText();
        }
        if(checkBorrowed.isSelected()){
            textBorrowed = "1";
        } else textBorrowed = "0";

        this.tblAddresses.setItems(BooksRepository.getInstance().search(textTitle,textSubject,textAuthor,textISBN,textDate,textStudent,textBorrowed));
    }
}
