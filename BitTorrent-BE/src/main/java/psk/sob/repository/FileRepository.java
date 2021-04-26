package psk.sob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psk.sob.repository.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, String> {
}
