package students;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mainFunctions.Users;

import java.sql.*;
import mainFunctions.UsersRepository;
public class StudentsRepository {
    private static StudentsRepository instance;

    private final String DATABASE_URL = "jdbc:derby:./db/students";

    private final String GET_ALL_QUERY = "SELECT * FROM students";
    private final String GET_ALL_DUE = "SELECT * FROM students WHERE fine != ? OR status = ?";

    private final String GET_QUERY = "SELECT * FROM students WHERE id=?";
    private final String GET_QUERY_NAME = "SELECT * FROM students WHERE firstName=?";
    private final String ADD_QUERY = "INSERT INTO students (firstName,lastName,studentID,login,password,roleS,status,fine) VALUES(?,?,?,?,?,?,?,?)";
    private final String DELETE_QUERY = "DELETE FROM students WHERE id=?";
    private final String GET_LAST_ID = "SELECT MAX(id) FROM students";
    private final String UPDATE_QUERY = "UPDATE students SET firstName=?, lastName=?, studentID=?,login=?,password=?,roleS=?,status=?,fine=? WHERE id = ?";
    private final String BLOCK = "UPDATE students SET status=? WHERE id = ?";
    private final String SET_FINE = "UPDATE students SET fine=? WHERE id = ?";

    private Connection connection;

    private PreparedStatement getAllStmt;
    private PreparedStatement getStmt;
    private PreparedStatement addStmt;
    private PreparedStatement deleteStmt;
    private PreparedStatement getLastIDStmt;
    private PreparedStatement updateStmt;
    private PreparedStatement block;
    private PreparedStatement getStmtName;
    private PreparedStatement giveFine;
    private PreparedStatement getAllDue;

    StudentsRepository() throws SQLException {
        this.connection = DriverManager.getConnection(DATABASE_URL);

        this.getAllStmt = this.connection.prepareStatement(GET_ALL_QUERY);
        this.getStmt = this.connection.prepareStatement(GET_QUERY);
        this.addStmt = this.connection.prepareStatement(ADD_QUERY);
        this.deleteStmt = this.connection.prepareStatement(DELETE_QUERY);
        this.getLastIDStmt = this.connection.prepareStatement(GET_LAST_ID);
        this.updateStmt = this.connection.prepareStatement(UPDATE_QUERY);
        this.getStmtName = this.connection.prepareStatement(GET_QUERY_NAME);
        this.giveFine = this.connection.prepareStatement(SET_FINE);
        this.getAllDue = this.connection.prepareStatement(GET_ALL_DUE);
        this.block = this.connection.prepareStatement(BLOCK);

    }

    public static StudentsRepository getInstance() throws SQLException {
        if (instance == null) {
            instance = new StudentsRepository();
        }
        return instance;
    }

    public Students get(String id) throws SQLException {
        ResultSet resultSet = null;
        Students student = null;
        this.getStmt.setString(1, id);
        resultSet = this.getStmt.executeQuery();

        if (resultSet.next()) {
            student = new Students(
                    resultSet.getString("id"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("studentID"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("roleS"),
                    resultSet.getString("status"),
                    resultSet.getString("fine")
            );
        }

        return student;
    }
    public Students getByName(String name) throws SQLException {
        ResultSet resultSet = null;
        Students student = null;
        this.getStmtName.setString(1, name);
        resultSet = this.getStmtName.executeQuery();

        if (resultSet.next()) {
            student = new Students(
                    resultSet.getString("id"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("studentID"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("roleS"),
                    resultSet.getString("status"),
                    resultSet.getString("fine")
            );
        }

        return student;
    }
    public ObservableList<Students> getAll() throws SQLException {
        ResultSet resultSet = null;
        ObservableList<Students> lists = FXCollections.observableArrayList();

        resultSet = this.getAllStmt.executeQuery();

        while (resultSet.next()){
            lists.add(new Students(
                    resultSet.getString("id"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("studentID"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("roleS"),
                    resultSet.getString("status"),
                    resultSet.getString("fine")
            ));
        }

        return lists;
    }
    public ObservableList<Students> getAllDue() throws SQLException {
        ResultSet resultSet = null;
        ObservableList<Students> lists = FXCollections.observableArrayList();
        this.getAllDue.setString(1, "0");
        this.getAllDue.setString(2, "1");

        resultSet = this.getAllDue.executeQuery();

        while (resultSet.next()){
            lists.add(new Students(
                    resultSet.getString("id"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("studentID"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("roleS"),
                    resultSet.getString("status"),
                    resultSet.getString("fine")
            ));
        }

        return lists;
    }
    public String add(Students student) throws SQLException {

        this.addStmt.setString(1, student.getFirstName());
        this.addStmt.setString(2, student.getLastName());
        this.addStmt.setString(3, student.getStudentID());
        this.addStmt.setString(4,student.getLogin());
        this.addStmt.setString(5,student.getPassword());
        this.addStmt.setString(6,student.getRole());
        this.addStmt.setString(7,student.getStatus());
        this.addStmt.setString(8,student.getFine());

        if(this.addStmt.executeUpdate() > 0){
            ResultSet lastResult = this.getLastIDStmt.executeQuery();
            if(lastResult.next()){
                return lastResult.getString(1);
            }
        }

        return null;
    }
    public void update(String id, String firstName, String lastName, String studentID,String login,String password,String roleS,String status,String fine) throws SQLException {

        this.updateStmt.setString(1, firstName);
        this.updateStmt.setString(2, lastName);
        this.updateStmt.setString(3, studentID);
        this.updateStmt.setString(4,login);
        this.updateStmt.setString(5,password);
        this.updateStmt.setString(6,roleS);
        this.updateStmt.setString(7,status);
        this.updateStmt.setString(8,fine);
        this.updateStmt.setString(9, id);
        this.updateStmt.executeUpdate();
    }

    public void block (String id) throws SQLException {
        this.block.setString(1, "1");
        this.block.setString(2, id);
        this.block.executeUpdate();
    }
    public void setFine (String id, String fine) throws SQLException {
        this.giveFine.setString(1, fine);
        this.giveFine.setString(2, id);
        this.giveFine.executeUpdate();
    }
    public void unblock (String id) throws SQLException {
        this.block.setString(1, "0");
        this.block.setString(2, id);
        this.block.executeUpdate();
    }

    public void delete(String id) throws SQLException {
        this.deleteStmt.setString(1, id);
        this.deleteStmt.executeUpdate();
    }

}
