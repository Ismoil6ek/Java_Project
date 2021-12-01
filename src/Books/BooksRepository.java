package Books;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class BooksRepository {
    private static BooksRepository instance;

    private final String DATABASE_URL = "jdbc:derby:./db/books";

    private final String GET_ALL_QUERY = "SELECT * FROM books";
    private final String GET_QUERY = "SELECT * FROM books WHERE id=?";
    private final String GET_QUERY_STUDENT = "SELECT * FROM books WHERE student=?";
    private final String ADD_QUERY = "INSERT INTO books (title, isbn, subject, author, publishedDate, student) VALUES(?,?,?,?,?,?)";
    private final String DELETE_QUERY = "DELETE FROM books WHERE id=?";
    private final String GET_LAST_ID = "SELECT MAX(id) FROM books";
    private final String UPDATE_QUERY = "UPDATE books SET title=?, isbn=?, subject=?, author=?, publishedDate=?, student=? WHERE id = ?";
    private final String SEARCH = "SELECT * FROM books WHERE (title=? OR isbn=? OR subject=? OR author=? OR publishedDate=? OR student=?) OR borrowed=?";
    private final String GET_BOOK ="UPDATE books SET borrowed=?, student=? WHERE id = ?";

    private final String GET_GIVEN = "SELECT given FROM books WHERE id=?";
    private final String GET_LIMIT = "SELECT limit FROM books WHERE id=?";
    private final String SET_GIVEN = " UPDATE books SET given=? WHERE id=?";
    private final String SET_LIMIT = "UPDATE books SET limit=? WHERE id=?";

    private Connection connection;

    private PreparedStatement getAllStmt;
    private PreparedStatement getStmt;
    private PreparedStatement addStmt;
    private PreparedStatement deleteStmt;
    private PreparedStatement getLastIDStmt;
    private PreparedStatement updateStmt;
    private PreparedStatement search;
    private PreparedStatement getBook;
    private PreparedStatement giveBook;

    private PreparedStatement getGiven;
    private PreparedStatement getLimit;
    private PreparedStatement setGiven;
    private PreparedStatement setLimit;


    BooksRepository() throws SQLException {
        this.connection = DriverManager.getConnection(DATABASE_URL);
        this.getAllStmt = this.connection.prepareStatement(GET_ALL_QUERY);
        this.getStmt = this.connection.prepareStatement(GET_QUERY);
        this.getBook = this.connection.prepareStatement(GET_QUERY_STUDENT);
        this.addStmt = this.connection.prepareStatement(ADD_QUERY);
        this.deleteStmt = this.connection.prepareStatement(DELETE_QUERY);
        this.getLastIDStmt = this.connection.prepareStatement(GET_LAST_ID);
        this.updateStmt = this.connection.prepareStatement(UPDATE_QUERY);
        this.search = this.connection.prepareStatement(SEARCH);
        this.getBook = this.connection.prepareStatement(GET_QUERY_STUDENT);
        this.giveBook = this.connection.prepareStatement(GET_BOOK);

        this.getGiven = this.connection.prepareStatement(GET_GIVEN);
        this.getLimit = this.connection.prepareStatement(GET_LIMIT);
        this.setGiven = this.connection.prepareStatement(SET_GIVEN);
        this.setLimit = this.connection.prepareStatement(SET_LIMIT);

    }

    public static BooksRepository getInstance() throws SQLException {
        if(instance == null){
            instance = new BooksRepository();
        }

        return instance;
    }
    //Get created date
    public String getGiven(String id) throws SQLException {
        ResultSet resultSet = null;
        String date = null;

        this.getGiven.setString(1, id);
        resultSet = this.getGiven.executeQuery();
        if(resultSet.next()){
            date = resultSet.getString(1);
        }
        return date;
    }
    //Get created date
    public String getLimit(String id) throws SQLException {
        ResultSet resultSet = null;
        String date = null;

        this.getLimit.setString(1, id);
        resultSet = this.getLimit.executeQuery();
        if(resultSet.next()){
            date = resultSet.getString(1);
        }
        return date;
    }
    //Get created date
    public void setGiven(String id, String date) throws SQLException {
        this.setGiven.setString(1,date);
        this.setGiven.setString(2,id);

        this.setGiven.executeUpdate();
    }
    //Get created date
    public void setLimit(String id, String date) throws SQLException {
        this.setLimit.setString(1,date);
        this.setLimit.setString(2,id);
        this.setLimit.executeUpdate();
    }
    public void giveBook(String id, String borrowed, String student) throws SQLException {
        this.giveBook.setString(1,borrowed);
        this.giveBook.setString(2,student);
        this.giveBook.setString(3,id);

        this.giveBook.executeUpdate();
    }

    public Books get(String id) throws SQLException {
        ResultSet resultSet = null;
        Books book = null;

        this.getStmt.setString(1, id);
        resultSet = this.getStmt.executeQuery();
        if(resultSet.next()){
            book = new Books(
                    resultSet.getString("id"),
                    resultSet.getString("title"),
                    resultSet.getString("author"),
                    resultSet.getString("subject"),
                    resultSet.getString("isbn"),
                    resultSet.getString("publishedDate"),
                    resultSet.getString("student"),
                    resultSet.getString("borrowed")

            );
        }

        return book;
    }

    public ObservableList<Books> getBook(String studentid) throws SQLException {
        ResultSet resultSet = null;
        ObservableList<Books> list = FXCollections.observableArrayList();

        this.getBook.setString(1, studentid);

        resultSet = this.getBook.executeQuery();
        while (resultSet.next()){
            list.add(new Books(
                    resultSet.getString("id"),
                    resultSet.getString("title"),
                    resultSet.getString("author"),
                    resultSet.getString("subject"),
                    resultSet.getString("isbn"),
                    resultSet.getString("publishedDate"),
                    resultSet.getString("student"),
                    resultSet.getString("borrowed")

            ));
        }

        return list;
    }
    public ObservableList<Books> getAll() throws SQLException {
        ResultSet resultSet = null;
        ObservableList<Books> list = FXCollections.observableArrayList();

        resultSet = this.getAllStmt.executeQuery();

        while (resultSet.next()){
            list.add(new Books(
                    resultSet.getString("id"),
                    resultSet.getString("title"),
                    resultSet.getString("author"),
                    resultSet.getString("subject"),
                    resultSet.getString("isbn"),
                    resultSet.getString("publishedDate"),
                    resultSet.getString("student"),
                    resultSet.getString("borrowed")

            ));
        }

        return list;
    }
    //SELECT * FROM books WHERE title=? OR isbn=? OR subject=? OR author=? OR publishedDate=? OR student=?
    public ObservableList<Books> search(String Title, String Subject, String Author, String isbn, String Date, String Student, String Borrowed) throws SQLException {
        ResultSet resultSet = null;
        ObservableList<Books> list = FXCollections.observableArrayList();

        this.search.setString(1, Title);
        this.search.setString(2, isbn);
        this.search.setString(3, Subject);
        this.search.setString(4, Author);
        this.search.setString(5, Date);
        this.search.setString(6, Student);
        this.search.setString(7, Borrowed);
        resultSet = this.search.executeQuery();
        while (resultSet.next()){
            list.add(new Books(
                    resultSet.getString("id"),
                    resultSet.getString("title"),
                    resultSet.getString("author"),
                    resultSet.getString("subject"),
                    resultSet.getString("isbn"),
                    resultSet.getString("publishedDate"),
                    resultSet.getString("student"),
                    resultSet.getString("borrowed")

            ));
        }

        return list;
    }

    //returns the id of the new row

    public String add(Books book) throws SQLException {
        this.addStmt.setString(1, book.getTitle());
        this.addStmt.setString(4, book.getAuthor());
        this.addStmt.setString(3, book.getSubject());
        this.addStmt.setString(2, book.getIsbn());
        this.addStmt.setString(5, book.getPublishedDate());
        this.addStmt.setString(6, book.getStudent());
        //returns number of rows updated
        if(this.addStmt.executeUpdate() > 0){
            ResultSet lastResult = this.getLastIDStmt.executeQuery();
            if(lastResult.next()){
                return lastResult.getString(1);
            }
        }

        return null;
    }

    // delete book
    public void delete(String id) throws SQLException {
        this.deleteStmt.setString(1, id);
        this.deleteStmt.executeUpdate();

    }
    //getBook - return book from students
    public void getBook(String id, String student) throws SQLException {

        this.getBook.setString(1, "0");
        this.getBook.setString(2, "0");
        this.getBook.setString(3, id);
        this.getBook.executeUpdate();
    }
    //setBook - give book to students
    public void setBook(String id, String student) throws SQLException {

        this.getBook.setString(1, "1");
        this.getBook.setString(2, student);
        this.getBook.setString(3, id);
        this.getBook.executeUpdate();
    }
    //title=?, isbn=?, subject=?, author=?, publishedDate=?
    public void update(String id, String title, String isbn, String subject, String author, String publishedDate, String student) throws SQLException {

        this.updateStmt.setString(1, title);
        this.updateStmt.setString(2, isbn);
        this.updateStmt.setString(3, subject);
        this.updateStmt.setString(4, author);
        this.updateStmt.setString(5, publishedDate);
        this.updateStmt.setString(6, student);
        this.updateStmt.setString(7, id);
        this.updateStmt.executeUpdate();
    }
    // view one book
    public void view(String id) throws SQLException {
        this.deleteStmt.setString(1, id);
        this.deleteStmt.executeUpdate();

    }
}
