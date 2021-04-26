package psk.sob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psk.sob.repository.entity.TrackerEntity;

@Repository
public interface TrackerRepository extends JpaRepository<TrackerEntity, String> {
}
