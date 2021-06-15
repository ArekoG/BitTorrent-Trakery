package psk.sob.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psk.sob.entity.File;

public interface FileRepository extends JpaRepository<File, Integer> {
    File findById(int fileId);
}
