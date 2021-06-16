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
import java.nio.charset.StandardCharsets;
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
        File file = new File("files" + File.separator + +fileDownloadInformation.getUserId() + File.separator + fileId + ".txt");
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        int bytesRead = fis.read(data);
        log.debug("Bytes read:" + bytesRead);
        fis.close();
        log.info("[UserId:" + fileDownloadInformation.getUserId() + " start sending file. DataTransferId:" + dataTransferId + "]");
        String fileContent = new String(data, StandardCharsets.UTF_8);
        for (int i = fileDownloadInformation.getStart(); i <= fileDownloadInformation.getStop(); i++) {
            DataTransfer dataTransfer = dataTransferRepository.findById(dataTransferId).orElseThrow(RuntimeException::new);
            if (!"inactive".equals(dataTransfer.getStatus())) {
                Map<String, String> variables = new HashMap<>();
                variables.put("userId", String.valueOf(userId));
                restTemplate.postForObject("http://localhost:8081/client/users/" + userId + "/receive/" + fileContent.charAt(i), Void.class, Object.class);
                Thread.sleep(2500);
            } else {
                log.error("[The download has been stopped. DataTransferId:" + dataTransferId + ", receiverId:" + userId + ", senderId:" + fileDownloadInformation.getUserId() + "]");
                throw new RuntimeException();
            }

        }
        log.info("[UserId:" + fileDownloadInformation.getUserId() + " stop sending file. DataTransferId:" + dataTransferId + "]");

    }
}
