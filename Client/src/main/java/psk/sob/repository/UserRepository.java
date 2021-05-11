package psk.sob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psk.sob.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);
}
