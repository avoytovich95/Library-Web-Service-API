package database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Alex on 4/20/2018.
 */
@Entity
@Table(name = "book")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {

    // Book id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private int id;

    @JsonProperty
    private String title;

    @JsonProperty
    private String author;

    @JsonProperty
    private int year;

    @JsonProperty
    private boolean status;

    @JsonProperty
    private String date;

    private String guest;

    public Book(int id, String title, String author, int year, boolean status, String guest, String date) {
        this.id = id;
        this.title = title;  this.author = author;
        this.year = year;    this.status = status;
        this.guest = guest;     this.date = date;
    }

    public Book(String title, String author, int year) {
        this.title = title; this.author = author; this.year = year;
    }

    public Book() {}

    @JsonIgnore
    public int getId() { return id; }
    public void setId(int id) {this.id = id; }

    @JsonIgnore
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    @JsonIgnore
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    @JsonIgnore
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    @JsonIgnore
    public boolean getStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    @JsonIgnore
    public String getGuest() { return guest; }
    public void setGuest(String guest) { this.guest = guest; }

    @JsonIgnore
    public String getDate() { return date; }
    public void setDate(String date) {this.date = date; }
}
