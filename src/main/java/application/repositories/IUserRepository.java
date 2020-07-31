package application.repositories;

import application.entities.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findUserByEmail(String email);
    User findByActivationCode(String code);
}


