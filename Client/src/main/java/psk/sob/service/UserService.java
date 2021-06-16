package psk.sob.service;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import psk.sob.dto.FileDownloadInformation;
import psk.sob.entity.DataTransfer;
import psk.sob.entity.TrackerUsersList;
import psk.sob.entity.User;
import psk.sob.entity.repository.DataTransferRepository;
import psk.sob.entity.repository.TrackerRepository;
import psk.sob.entity.repository.TrackerUserListRepository;
import psk.sob.entity.repository.UserRepository;
import psk.sob.publisher.SpringEventPublisher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private TrackerRepository trackerRepository;
    private DataTransferRepository dataTransferRepository;
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
        Integer count = userRepository.countActiveUserByUserId(userId);
        if (count == 0) {
            return ResponseEntity.status(500)
                    .build();
        }
        return ResponseEntity.status(200)
                .build();
    }

    public void downloadFile(int userId, int fileId, int trackerId) {
        Integer userActive = userRepository.isUserActive(userId, trackerId);
        if (userActive == 0)
            return;
        Map<String, String> variables = new HashMap<>();
        variables.put("fileId", String.valueOf(fileId));
        variables.put("trackerId", String.valueOf(trackerId));
        ResponseEntity<List<FileDownloadInformation>> exchange = getFileDownloadInfo(variables);
        DataTransfer dataTransfer = getDataTransfer(userId, exchange);
        dataTransferRepository.save(dataTransfer);
        springEventPublisher.startDownloading(exchange.getBody(), userId, fileId, dataTransfer.getId());
    }

    private DataTransfer getDataTransfer(int userId, ResponseEntity<List<FileDownloadInformation>> exchange) {
        User user = userRepository.findById(userId)
                .get();
        DataTransfer dataTransfer = new DataTransfer();
        dataTransfer.setUser(user);
        dataTransfer.setUsersFrom(exchange.getBody().stream()
                .map(FileDownloadInformation::getUserId)
                .collect(Collectors.toList()));
        dataTransfer.setStatus("active");
        return dataTransfer;
    }

    private ResponseEntity<List<FileDownloadInformation>> getFileDownloadInfo(Map<String, String> variables) {
        return restTemplate.exchange("http://localhost:8080/bit-torrent/users/files/{fileId}/download/{trackerId}",
                HttpMethod.POST, null, new ParameterizedTypeReference<List<FileDownloadInformation>>() {
                }, variables);
    }

    public ResponseEntity isUserAliveInTracker(int trackerId, int userId) {
        Integer count = trackerUserListRepository.countTrackerUsersListByUserIdAndTrackerId(trackerId, userId);
        if (count == 0) {
            return ResponseEntity.status(500)
                    .build();
        }
        return ResponseEntity.status(200)
                .build();
    }
}
