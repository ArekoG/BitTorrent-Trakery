package psk.sob.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import psk.sob.entity.DataTransfer;

import java.util.List;

public interface DataTransferRepository extends JpaRepository<DataTransfer, Integer> {
    @Query("SELECT DISTINCT dt FROM DataTransfer dt JOIN FETCH dt.usersFrom")
    List<DataTransfer> findAll();

    @Query("SELECT DISTINCT dt FROM DataTransfer dt JOIN FETCH dt.usersFrom WHERE dt.status = 'active'")
    List<DataTransfer> findAllActive();

    @Query("SELECT DISTINCT dt FROM DataTransfer dt JOIN FETCH dt.usersFrom WHERE dt.status = 'inactive'")
    List<DataTransfer> findAllInactive();
}
