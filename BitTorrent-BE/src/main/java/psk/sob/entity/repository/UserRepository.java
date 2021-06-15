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

    @Query(value = "select lofo.user_id from list_of_file_owners lofo \n" +
            "            where lofo.file_id = :fileId \n" +
            "            and lofo.user_id in \n" +
            "               (select tul.user_id from tracker_users_list tul \n" +
            "                where tul.tracker_id = :trackerId \n" +
            "                and tul.status = 'enable')", nativeQuery = true)
    List<Integer> listOfUsersWhoHaveTheFile(@Param("fileId") int fileId, @Param("trackerId") int trackerId);
}
