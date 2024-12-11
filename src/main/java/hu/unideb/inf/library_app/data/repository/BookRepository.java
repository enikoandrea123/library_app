package hu.unideb.inf.library_app.data.repository;

import hu.unideb.inf.library_app.data.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query("SELECT b FROM BookEntity b " +
            "WHERE (:genre IS NULL OR b.genre = :genre) " +
            "AND (:author IS NULL OR LOWER(b.author)=:author) " +
            "AND (:title IS NULL OR LOWER(b.title)=:title) " +
            "AND (:isbn IS NULL OR b.isbn = :isbn) " +
            "AND (:year IS NULL OR b.publicationYear = :year)")
    List<BookEntity> findByFilters(@Param("genre") String genre,
                                   @Param("author") String author,
                                   @Param("title") String title,
                                   @Param("isbn") Integer isbn,
                                   @Param("year") Integer year);
}

