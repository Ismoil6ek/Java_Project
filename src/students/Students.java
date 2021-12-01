package students;


public class Students{
    private String id;
    private String firstName;
    private String lastName;
    private String studentID;
    private String login;
    private String password;
    private String roleS;
    private String status;
    private String fine;

    public Students(String id,String firstName,String lastName,String studentID,String login,String password,String roleS,String status,String fine)
    {
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.studentID=studentID;
        this.login=login;
        this.password=password;
        this.roleS=roleS;
        this.status=status;
        this.fine=fine;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return roleS;
    }

    public void setRole(String roleS) {
        this.roleS = roleS;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }
}
