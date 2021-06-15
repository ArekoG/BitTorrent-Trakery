package psk.sob.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import psk.sob.dto.FileDownloadInformation;
import psk.sob.entity.DataTransfer;
import psk.sob.entity.repository.DataTransferRepository;
import psk.sob.publisher.SenderEvent;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SenderThreadService implements Runnable {

    private FileDownloadInformation fileDownloadInformation;
    private int userId;
    private RestTemplate restTemplate = new RestTemplate();
    private DataTransferRepository dataTransferRepository;
    private int fileId;
    private int dataTransferId;

    public SenderThreadService(FileDownloadInformation fileDownloadInformation, SenderEvent senderEvent, DataTransferRepository dataTransferRepository) {
        this.fileDownloadInformation = fileDownloadInformation;
        this.userId = senderEvent.getUserId();
        this.fileId = senderEvent.getFileId();
        this.dataTransferId = senderEvent.getDataTransferId();
        this.dataTransferRepository = dataTransferRepository;
    }

    @SneakyThrows
    @Override
    public void run() {
        File file = new File("files/" + fileDownloadInformation.getUserId() + "/" + fileId + ".txt");
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();

        String fileContent = new String(data, "UTF-8");
        for (int i = fileDownloadInformation.getStart(); i <= fileDownloadInformation.getStop(); i++) {
            DataTransfer dataTransfer = dataTransferRepository.findById(dataTransferId).orElseThrow(RuntimeException::new);
            if (dataTransfer.getStatus() != "inactive") {
                Map<String, String> variables = new HashMap<>();
                variables.put("userId", String.valueOf(userId));
                restTemplate.postForObject("http://localhost:8081/client/users/" + userId + "/receive/" + fileContent.charAt(i), Void.class, Object.class);
                Thread.sleep(2500);
            } else {
                log.error("File transfer is no active!");
                throw new RuntimeException();
            }

        }

    }
}
