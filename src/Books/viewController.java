package Books;

import javafx.scene.control.TextField;
import students.Students;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import mainFunctions.Users;
import mainFunctions.UsersRepository;
import students.*;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import Books.*;
public class viewController implements Initializable {
    @FXML
    public TextField txtStudent;
    @FXML
    public TextField txtDay;
    @FXML
    public Label lblDueDate;
    Books currentBook;
    @FXML
    public Label lblTitle;
    @FXML
    public Label lblSubject;
    @FXML
    public Label lblAuthor;
    @FXML
    public Label lblISBN;
    @FXML
    public Label lblDate;
    @FXML
    public Label lblStudent;
    @FXML
    public Button btnReturn;
    @FXML
    public Button btnBorrow;
    @FXML

    public ComboBox cmbStudents;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void setData(Books book) {
        currentBook = book;
        Students student;

        try {
            student = StudentsRepository.getInstance().get(book.getStudent());
            lblAuthor.setText(book.getAuthor());
            lblISBN.setText(book.getIsbn());
            lblSubject.setText(book.getSubject());
            lblDate.setText(book.getPublishedDate());
            lblTitle.setText(book.getTitle());
            if ((student != null) && book.getBorrowed().equals("1")) {
                lblStudent.setText(student.getFirstName() + "   " + student.getLastName());
                lblDueDate.setText(BooksRepository.getInstance().getLimit(book.getId()));
            } else {
                lblStudent.setText("No one");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }


    }


    @FXML
    public void giveBook(ActionEvent actionEvent) throws SQLException {
        SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
        Students stud = (Students) StudentsRepository.getInstance().getByName(txtStudent.getText().trim());
        if(currentBook.getBorrowed().equals("0") && stud.getStatus().equals("0")){
            Calendar calendar = new GregorianCalendar();
            Calendar c = new GregorianCalendar();
            if(stud == null || txtDay.getText().length()==0){
                System.out.println(txtStudent.getText() + "not found or invalid days format");
            } else {
                int day = Integer.parseInt(txtDay.getText());
                calendar.add(Calendar.DAY_OF_MONTH, day);
                BooksRepository.getInstance().setGiven(currentBook.getId(),dtf.format(c.getTime()).toString());
                BooksRepository.getInstance().setLimit(currentBook.getId(),dtf.format(calendar.getTime()).toString());
                System.out.println(dtf.format(calendar.getTime()).toString());
                BooksRepository.getInstance().giveBook(currentBook.getId(),"1", stud.getId());

            }

        }


    }
    @FXML
    public void returnBook(ActionEvent actionEvent) throws SQLException {
        //check borrowed status
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
        Students stud = (Students) StudentsRepository.getInstance().get(currentBook.getStudent());
        if(currentBook.getBorrowed().equals("1") ){
            if(stud != null){
            try {
                Date given = dtf.parse(BooksRepository.getInstance().getGiven(currentBook.getId()));
                Date limit = dtf.parse(BooksRepository.getInstance().getLimit(currentBook.getId()));
                Date today = calendar.getTime();
                if(today.compareTo(limit)>0){
                    StudentsRepository.getInstance().setFine(stud.getId(), "15000");
                }
                BooksRepository.getInstance().giveBook(currentBook.getId(),"0", "0");

            } catch (ParseException e) {
                System.out.println("Invalid data format");
            }
        } else {
                System.out.println("student not found");

            }
        }
        else {
            System.out.println("Borrowed already or student not found");
        }

    }
}
