package psk.sob.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psk.sob.entity.DataTransfer;

public interface DataTransferRepository extends JpaRepository<DataTransfer, Integer> {
}
