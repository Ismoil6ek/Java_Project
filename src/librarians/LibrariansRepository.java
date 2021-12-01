package librarians;

import students.Students;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.*;

public class LibrariansRepository {
    private static LibrariansRepository instance;

    private final String DATABASE_URL = "jdbc:derby:./db/librarians";
    private final String GET_ALL_QUERY = "SELECT * FROM librarians";
    private final String GET_QUERY = "SELECT * FROM librarians WHERE id=?";
    private final String ADD_QUERY = "INSERT INTO librarians (nameN,login,password,roleN) VALUES(?,?,?,?)";
    private final String DELETE_QUERY = "DELETE FROM librarians WHERE id=?";
    private final String GET_LAST_ID = "SELECT MAX(id) FROM librarians";
    private final String UPDATE_QUERY = "UPDATE librarians SET nameN=?,login=?,password=?,roleN=? WHERE id=?";
    private final String FIND_BY_NAME = "SELECT * FROM librarians WHERE login=?";

    private Connection connection;

    private PreparedStatement getAllStmt;
    private PreparedStatement getStmt;
    private PreparedStatement addStmt;
    private PreparedStatement deleteStmt;
    private PreparedStatement getLastIDStmt;
    private PreparedStatement updateStmt;
    private PreparedStatement findByName;

    LibrariansRepository() throws SQLException {
        this.connection = DriverManager.getConnection(DATABASE_URL);
        this.getAllStmt = this.connection.prepareStatement(GET_ALL_QUERY);
        this.getStmt = this.connection.prepareStatement(GET_QUERY);
        this.addStmt = this.connection.prepareStatement(ADD_QUERY);
        this.deleteStmt = this.connection.prepareStatement(DELETE_QUERY);
        this.getLastIDStmt = this.connection.prepareStatement(GET_LAST_ID);
        this.updateStmt = this.connection.prepareStatement(UPDATE_QUERY);
        this.findByName = this.connection.prepareStatement(FIND_BY_NAME);

    }

    public static LibrariansRepository getInstance() throws SQLException {
        if (instance == null) {
            instance = new LibrariansRepository();
        }
        return instance;
    }

    public Librarians get(String id) throws SQLException {
        ResultSet resultSet = null;
        Librarians librarian = null;
        this.getStmt.setString(1, id);
        resultSet = this.getStmt.executeQuery();

        if (resultSet.next()) {
            librarian = new Librarians(
                    resultSet.getString("id"),
                    resultSet.getString("nameN"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("roleN")
            );
        }

        return librarian;
    }

    public ObservableList<Librarians> getAll() throws SQLException {
        ResultSet resultSet = null;
        ObservableList<Librarians> lists = FXCollections.observableArrayList();

        resultSet = this.getAllStmt.executeQuery();

        while (resultSet.next()){
            lists.add(new Librarians(
                    resultSet.getString("id"),
                    resultSet.getString("nameN"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("roleN")
            ));
        }

        return lists;
    }

    public String add(Librarians librarian) throws SQLException {
        this.addStmt.setString(1, librarian.getName());
        this.addStmt.setString(2, librarian.getLogin());
        this.addStmt.setString(3, librarian.getPassword());
        this.addStmt.setString(4,librarian.getRole());

        if(this.addStmt.executeUpdate() > 0){
            ResultSet lastResult = this.getLastIDStmt.executeQuery();
            if(lastResult.next()){
                return lastResult.getString(1);
            }
        }

        return null;
    }

    public void update(String id, String Name, String Login, String Password,String Role) throws SQLException {

        this.updateStmt.setString(1, Name);
        this.updateStmt.setString(2, Login);
        this.updateStmt.setString(3, Password);
        this.updateStmt.setString(4, Role);
        this.updateStmt.setString(5,id);
        this.updateStmt.executeUpdate();
    }

    public void delete(String id) throws SQLException {
        this.deleteStmt.setString(1, id);
        this.deleteStmt.executeUpdate();
    }
    public Librarians findByName(String name) throws SQLException {
        ResultSet resultSet = null;
        Librarians librarians = null;

        this.findByName.setString(1, name);
        resultSet = this.findByName.executeQuery();
        if(resultSet.next()){
            //nameN, login, password,roleN
            librarians = new Librarians(
                    resultSet.getString("id"),
                    resultSet.getString("nameN"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("roleN")
            );
        }


        return librarians;
    }
}
