package hu.unideb.inf.library_app.data.repository;

import hu.unideb.inf.library_app.data.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LoginRepository extends JpaRepository<LoginEntity, Long> {
    Optional<LoginEntity> findByUsername(String username);
}