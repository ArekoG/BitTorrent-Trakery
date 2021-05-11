package psk.sob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psk.sob.entity.TrackerUsersList;

public interface TrackerUserListRepository  extends JpaRepository<TrackerUsersList, Integer> {
}
