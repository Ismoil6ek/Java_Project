package librarians;

public class Librarians {
    private String id;
    private String Name;
    private String Login;
    private String Password;
    private String Role;

    public Librarians(String id,String Name,String Login,String Password,String Role)
    {
        this.id=id;
        this.Name=Name;
        this.Login=Login;
        this.Password=Password;
        this.Role=Role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}
