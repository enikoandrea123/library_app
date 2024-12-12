package hu.unideb.inf.library_app.data.repository;

import hu.unideb.inf.library_app.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT u FROM UserEntity u " +
            "WHERE (:name IS NULL OR LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:email IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%'))) " +
            "AND (:phoneNumber IS NULL OR u.phoneNumber LIKE CONCAT('%', :phoneNumber, '%')) " +
            "AND (:birthdate IS NULL OR u.birthdate = :birthdate)")
    List<UserEntity> findByFilters(@Param("name") String name,
                                   @Param("email") String email,
                                   @Param("phoneNumber") String phoneNumber,
                                   @Param("birthdate") Date birthdate);
}