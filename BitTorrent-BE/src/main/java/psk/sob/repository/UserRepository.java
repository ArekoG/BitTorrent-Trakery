package psk.sob.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import psk.sob.repository.entity.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, String> {
}
