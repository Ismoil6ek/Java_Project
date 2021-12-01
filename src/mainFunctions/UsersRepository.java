package mainFunctions;

import javafx.collections.*;

import java.sql.*;

public class UsersRepository {
    private static UsersRepository instance;

    private final String DATABASE_URL = "jdbc:derby:./db/users.sql";

    private final String GET_ALL_QUERY = "SELECT * FROM users";
    private final String GET_QUERY = "SELECT * FROM users WHERE id=?";
    private final String ADD_QUERY = "INSERT INTO users(login, name, role, password) VALUES(?,?,?,?)";
    private final String DELETE_QUERY = "DELETE FROM users WHERE id=?";
    private final String DELETE_QUERY_NAME = "DELETE FROM users WHERE login=?";
    private final String GET_LAST_ID = "SELECT MAX(id) FROM users";
    private final String FIND_BY_NAME = "SELECT * FROM users WHERE login=?";
    private final String UPDATE_QUERY = "UPDATE users SET login=?,name=?,role=?,password=? WHERE login=?";
    private Connection connection;

    private final PreparedStatement getAllStmt;
    private final PreparedStatement getStmt;
    private final PreparedStatement addStmt;
    private final PreparedStatement deleteStmt;
    private final PreparedStatement getLastIDStmt;
    private final PreparedStatement findByName;
    private final PreparedStatement deleteName;
    private PreparedStatement updateStmt;


    UsersRepository() throws SQLException {
        this.connection = DriverManager.getConnection(DATABASE_URL);
        this.getAllStmt = this.connection.prepareStatement(GET_ALL_QUERY);
        this.getStmt = this.connection.prepareStatement(GET_QUERY);
        this.addStmt = this.connection.prepareStatement(ADD_QUERY);
        this.deleteStmt = this.connection.prepareStatement(DELETE_QUERY);
        this.getLastIDStmt = this.connection.prepareStatement(GET_LAST_ID);
        this.findByName = this.connection.prepareStatement(FIND_BY_NAME);
        this.deleteName = this.connection.prepareStatement(DELETE_QUERY_NAME);
        this.updateStmt = this.connection.prepareStatement(UPDATE_QUERY);
    }

    //lazy instantiation
    public static UsersRepository getInstance() throws SQLException {
        if(instance == null){
            instance = new UsersRepository();
        }
        return instance;
    }

    //get, getAll, add, delete
    public Users get(String id) throws SQLException {
        ResultSet resultSet = null;
        Users user = null;

        this.getStmt.setString(1, id);
        resultSet = this.getStmt.executeQuery();
        if(resultSet.next()){
            user = new Users(
                    resultSet.getString("id"),
                    resultSet.getString("login"),
                    resultSet.getString("name"),
                    resultSet.getString("role"),
                    resultSet.getString("password")
            );
        }

        return user;
    }

    public Users findByName(String name) throws SQLException {
        ResultSet resultSet = null;
        Users user = null;

        this.findByName.setString(1, name);
        resultSet = this.findByName.executeQuery();
        if(resultSet.next()){
            user = new Users(
                    resultSet.getString("id"),
                    resultSet.getString("login"),
                    resultSet.getString("name"),
                    resultSet.getString("role"),
                    resultSet.getString("password")
            );
        }


        return user;
    }
    public void update(String Name, String oldLogin, String Login, String Password, String Role) throws SQLException {

        this.updateStmt.setString(1, Login);
        this.updateStmt.setString(2, Name);
        this.updateStmt.setString(3, Role);
        this.updateStmt.setString(4, Password);
        this.updateStmt.setString(5,oldLogin);
        this.updateStmt.executeUpdate();
    }
    public ObservableList<Users> getAll() throws SQLException {
        ResultSet resultSet = null;
        ObservableList<Users> list = FXCollections.observableArrayList();

        resultSet = this.getAllStmt.executeQuery();

        while (resultSet.next()){
            list.add(new Users(
                    resultSet.getString("id"),
                    resultSet.getString("login"),
                    resultSet.getString("name"),
                    resultSet.getString("role"),
                    resultSet.getString("password")
            ));
        }

        return list;
    }



    //returns the addressID of the new row
    public String add(Users user) throws SQLException {
        this.addStmt.setString(1, user.getLogin());
        this.addStmt.setString(2, user.getName());
        this.addStmt.setString(3, user.getRole());
        this.addStmt.setString(4, user.getPassword());

        //returns number of rows updated
        if(this.addStmt.executeUpdate() > 0){
            ResultSet lastResult = this.getLastIDStmt.executeQuery();
            if(lastResult.next()){
                return lastResult.getString(1);
            }
        }

        return null;
    }

    public void delete(String id) throws SQLException {
        this.deleteStmt.setString(1, id);
        this.deleteStmt.executeUpdate();

    }
    public void deleteName(String name) throws SQLException {
        this.deleteName.setString(1, name);
        this.deleteName.executeUpdate();

    }

}
