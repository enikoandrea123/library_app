package hu.unideb.inf.library_app.data.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "`user`")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "birthdate", nullable = false)
    private Date birthdate;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToMany(mappedBy = "users")
    private List<BookEntity> books;

    @OneToMany(mappedBy = "user")
    private List<BorrowEntity> borrows;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

    public List<BorrowEntity> getBorrows() {
        return borrows;
    }

    public void setBorrows(List<BorrowEntity> borrows) {
        this.borrows = borrows;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity that)) return false;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(birthdate, that.birthdate) && Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthdate, email, phoneNumber);
    }
}