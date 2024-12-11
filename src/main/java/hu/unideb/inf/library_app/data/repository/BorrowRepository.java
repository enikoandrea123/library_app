package hu.unideb.inf.library_app.data.repository;

import hu.unideb.inf.library_app.data.entity.BorrowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowEntity, Long> {

    @Query("SELECT b FROM BorrowEntity b " +
            "WHERE (:userName IS NULL OR LOWER(b.user.name) = :userName) " +
            "AND (:bookTitle IS NULL OR LOWER(b.book.title)= :bookTitle) " +
            "AND (:isbn IS NULL OR b.book.isbn = :isbn) " +
            "AND (:author IS NULL OR LOWER(b.book.author) = :author) " +
            "AND (:borrowDate IS NULL OR b.borrowDate = :borrowDate)")
    List<BorrowEntity> findByFilters(@Param("userName") String userName,
                                     @Param("bookTitle") String bookTitle,
                                     @Param("isbn") Integer isbn,
                                     @Param("author") String author,
                                     @Param("borrowDate") Date borrowDate);

}
