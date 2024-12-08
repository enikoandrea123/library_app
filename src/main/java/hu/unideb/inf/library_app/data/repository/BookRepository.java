package hu.unideb.inf.library_app.data.repository;

import hu.unideb.inf.library_app.data.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
