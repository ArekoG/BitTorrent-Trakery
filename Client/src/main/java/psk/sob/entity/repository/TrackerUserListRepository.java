package psk.sob.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import psk.sob.entity.TrackerUsersList;

import java.util.List;

public interface TrackerUserListRepository extends JpaRepository<TrackerUsersList, Integer> {
    TrackerUsersList findByTrackerIdAndUserId(int trackerId, int userId);
    List<TrackerUsersList> findAllByTrackerId(int trackerId);

    @Query(value = "select count(*) from tracker_users_list tul where tul.status = 'enable' \n" +
            "and tul.tracker_id = :trackerId \n" +
            "and tul.user_id = :userId", nativeQuery = true)
    Integer countTrackerUsersListByUserIdAndTrackerId(@Param("trackerId") int trackerId, @Param("userId") int userId);
}
