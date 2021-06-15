package psk.sob.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import psk.sob.entity.repository.DataTransferRepository;

@Service
@AllArgsConstructor
public class DataTransferService {
    private DataTransferRepository dataTransferRepository;

    @Transactional
    public void disableTransfer(int dataTransferId) {
        psk.sob.entity.DataTransfer dataTransfer = dataTransferRepository.findById(dataTransferId)
                .orElseThrow(RuntimeException::new);
        dataTransfer.setStatus("inactive");
        dataTransferRepository.save(dataTransfer);
    }
}
