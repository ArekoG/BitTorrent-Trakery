package psk.sob.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import psk.sob.entity.Tracker;

import java.util.List;

public interface TrackerRepository extends JpaRepository<Tracker, Integer> {
    @Query(value = "select * from tracker t\n" +
            "join tracker_users_list ts on t.tracker_id  = ts.tracker_id \n" +
            "join users u on ts.user_id  = u.user_id where u.login = :login and t.status = 'enable'",nativeQuery = true)
    List<Tracker>findAllByLoginAndStatus(@Param("login") String login);

}
