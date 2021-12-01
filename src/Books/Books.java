package Books;

import java.util.Date;

public class Books {


    private String id;
    private String title;
    private String author;
    private String subject;
    private String isbn;
    private String publishedDate;
    private String student;
    private String borrowed;
    private String given;
    private String limit;


    public Books(String id, String title, String author, String subject, String isbn, String publishedDate, String student, String borrowed){
        this.id = id;
        this.author = author;
        this.title = title;
        this.subject=subject;
        this.isbn=isbn;
        this.publishedDate=publishedDate;
        this.student = student;
        this.borrowed = borrowed;
//        this.given = given;
//        this.limit = limit;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author){
        this.author = author;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(String borrowed) {
        this.borrowed = borrowed;
    }

    public String getGiven() {
        return given;
    }

    public void setGiven(String given) {
        this.given = given;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}
