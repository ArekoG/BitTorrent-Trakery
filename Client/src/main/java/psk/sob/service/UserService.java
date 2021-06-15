package psk.sob.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import psk.sob.dto.FileDownloadInformation;
import psk.sob.entity.TrackerUsersList;
import psk.sob.entity.User;
import psk.sob.entity.repository.TrackerRepository;
import psk.sob.entity.repository.TrackerUserListRepository;
import psk.sob.entity.repository.UserRepository;
import psk.sob.publisher.SpringEventPublisher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private TrackerRepository trackerRepository;
    private TrackerUserListRepository trackerUserListRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private SpringEventPublisher springEventPublisher;

    public void enableUser(String login) {
        User user = userRepository.findByLogin(login);
        user.setStatus("enable");
        userRepository.save(user);
    }

    public void disableUser(String login) {
        User user = userRepository.findByLogin(login);
        user.setStatus("disable");
        userRepository.save(user);
    }

    public void assignUserToTheTracker(int trackerId, String login) {
        User user = userRepository.findByLogin(login);
        TrackerUsersList trackerUsersList = new TrackerUsersList();
        trackerUsersList.setUser(user);
        trackerUsersList.setTracker(trackerRepository.findById(trackerId).orElseThrow(RuntimeException::new));
        trackerUsersList.setStatus(user.getStatus());
        trackerUserListRepository.save(trackerUsersList);
    }


    public ResponseEntity isUserAlive(int userId) {
        Integer count = userRepository.countActiveUserByUserIdAndTrackerId(userId);
        if (count == 0) {
            return ResponseEntity.status(500)
                    .build();
        }
        return ResponseEntity.status(200)
                .build();

    }

    public void downloadFile(int userId, String fileName, int trackerId) {
        Map<String, String> variables = new HashMap<>();
        variables.put("fileName", fileName);
        variables.put("userId", String.valueOf(userId));
        variables.put("trackerId", String.valueOf(trackerId));
        restTemplate.postForObject("http://localhost:8080/bit-torrent/users/{userId}/files/{fileName}/download", Void.class, Object.class, variables);
    }

    @SneakyThrows
    public void startDownloading(List<FileDownloadInformation> fileDownloadInformation, int userId) {
        springEventPublisher.startDownloading(fileDownloadInformation, userId);
    }
}
