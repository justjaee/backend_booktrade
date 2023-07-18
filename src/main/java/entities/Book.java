package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer id;
    @Column(nullable = false)
    private String title;
    private String author;
    @Column(name = "book_image")
    private String bookImage;
    //            (use the @Column annotation and set the name = “years_of_experience” so that it keeps to the naming convention for mysql)
    @Column(name = "book_condition")
    private String bookCondition;



    @ManyToOne
    @JoinColumn(name = "current_book_id") // foreign key
    @JsonIgnore

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public String getBookCondition() {
        return bookCondition;
    }

    public void setCondition(String condition) {
        this.bookCondition = condition;
    }
}
