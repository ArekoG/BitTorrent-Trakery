package psk.sob.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import psk.sob.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);
    Optional<User> findById(Integer id);

    @Query(value = "select * from users u\n" +
            "join tracker_users_list ts on u.user_id  = ts.user_id \n" +
            "join tracker t on ts.user_id  = t.tracker_id where ts.tracker_id = :id and ts.status = 'enable'", nativeQuery = true)
    List<User> findByTrackerId(@Param("id") int id);

    @Query(value = "select u from users u\n" +
            "join file f on u.user_id = f.user_id \n" +
            "join tracker_users_list tul on u.user_id = tul.user_id\n" +
            "join tracker t on tul.user_id = t.tracker_id\n" +
            "where f.name = :fileName and tul.tracker_id = :trackerId", nativeQuery = true)
    List<User> listOfUserWhoHaveTheFile(@Param("fileName") String fileName, @Param("trackerId") int trackerId);
}
