package psk.sob.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import psk.sob.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);

    @Query(value = "select count(*) from users u where u.status = 'enable' and u.user_id=:userId", nativeQuery = true)
    Integer countActiveUserByUserId(@Param("userId") int userId);

    @Query(value = "select count(*) from users u \n" +
            "join tracker_users_list ts on u.user_id  = ts.user_id \n" +
            "join tracker t on ts.tracker_id  = t.tracker_id \n" +
            "where u.user_id  =:userId and ts.status  = 'enable' and ts.tracker_id=:trackerId", nativeQuery = true)
    Integer isUserActive(@Param("userId") int userId,@Param("trackerId") int trackerId);
}
