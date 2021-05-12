package psk.sob.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import psk.sob.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);

    @Query(value = "select count(*) from users u where u.status = 'enable' and u.user_id=:userId", nativeQuery = true)
    Integer countActiveUserByUserIdAndTrackerId(@Param("userId") int userId);
}
