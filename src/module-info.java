module group.project.java.coders {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires javafx.graphics;

    opens loginDialog;
    opens studentUser;
    opens Books;
    opens students;
    opens librarians;
}