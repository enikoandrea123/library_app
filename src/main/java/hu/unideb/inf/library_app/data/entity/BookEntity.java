package hu.unideb.inf.library_app.data.entity;

import jakarta.persistence.*;

import javax.lang.model.element.Name;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "isbn", nullable = false)
    private Integer isbn;
    @Column(name = "genre")
    private String genre;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "publication_year", nullable = false)
    private Date publicationYear;

    public BookEntity() {

    }

    public BookEntity(long id, String title, String author, Integer isbn, String genre, Integer quantity, Date publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.quantity = quantity;
        this.publicationYear = publicationYear;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Date publicationYear) {
        this.publicationYear = publicationYear;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookEntity that)) return false;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(author, that.author) && Objects.equals(isbn, that.isbn) && Objects.equals(genre, that.genre) && Objects.equals(quantity, that.quantity) && Objects.equals(publicationYear, that.publicationYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, isbn, genre, quantity, publicationYear);
    }
}
