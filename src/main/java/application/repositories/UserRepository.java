package application.repositories;

import application.entities.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    Optional<User> findUserByEmail(String email);
    Optional<User> findByActivationCode(String code);
    @Query(value = "SELECT * FROM user WHERE email = ?1 AND active AND activation_code IS NULL", nativeQuery = true)
    User findActiveUserWithoutActivationCodeByEmail(String email);
}


