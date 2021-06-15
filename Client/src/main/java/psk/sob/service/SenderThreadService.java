package psk.sob.service;

import lombok.SneakyThrows;
import org.springframework.web.client.RestTemplate;
import psk.sob.dto.FileDownloadInformation;
import psk.sob.entity.repository.UserRepository;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class SenderThreadService implements Runnable {

    private FileDownloadInformation fileDownloadInformation;
    private int userId;
    private RestTemplate restTemplate = new RestTemplate();
    private UserRepository userRepository;
    private int fileId;
    public SenderThreadService(FileDownloadInformation fileDownloadInformation, int userId, UserRepository userRepository,int fileId) {
        this.fileDownloadInformation = fileDownloadInformation;
        this.userId = userId;
        this.userRepository = userRepository;
        this.fileId = fileId;
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
        for (int i = fileDownloadInformation.getStart(); i < fileDownloadInformation.getStop(); i++) {
            Integer active = userRepository.countActiveUserByUserIdAndTrackerId(fileDownloadInformation.getUserId());
            if (active > 0) {
                Map<String, String> variables = new HashMap<>();
                variables.put("userId", String.valueOf(userId));
                restTemplate.postForObject("http://localhost:8081/client/users/" + userId + "/receive/" + fileContent.charAt(i), Void.class, Object.class);
                Thread.sleep(1000);
            }

        }

    }
}
