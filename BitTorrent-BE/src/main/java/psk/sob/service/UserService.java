package psk.sob.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import psk.sob.dto.File;
import psk.sob.dto.FileDownloadInformation;
import psk.sob.dto.User;
import psk.sob.entity.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public List<File> getFiles(String userId) {
        return null;
    }

    public List<User> getUsers() {
        List<User> userListAfterMapping = new ArrayList<>();
        List<psk.sob.entity.User> userList = userRepository.findAll();
        userList.forEach(i -> userListAfterMapping.add(
                User.builder()
                        .userId(i.getId())
                        .userStatus(i.getStatus())
                        .userLogin(i.getLogin())
                        .build()));
        return userListAfterMapping;
    }

    @Transactional
    public void enableUser(int userId) {
        psk.sob.entity.User user = userRepository.findById(userId)
                .orElseThrow(RuntimeException::new);
        user.setStatus("enable");
        userRepository.save(user);
    }

    @Transactional
    public void disableUser(int userId) {
        psk.sob.entity.User user = userRepository.findById(userId)
                .orElseThrow(RuntimeException::new);
        user.setStatus("disable");
        userRepository.save(user);
    }

    public void divideFileAndStartDownloading(int userId, String fileName) {
        //podzielnie plikow oraz przygotowanie dto

        Map<String, Integer> userToVariables = new HashMap<>();
        userToVariables.put("userId", userId);

        List<FileDownloadInformation> fileDownloadInformation = new ArrayList<>();
        fileDownloadInformation.add(FileDownloadInformation.builder()
                .fileName("test")
                .userId(1)
                .start(0)
                .stop(5)
                .build());
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.postForEntity("http://localhost:8081/client/users/1/start-download", fileDownloadInformation, Object.class);//to id 1 trzeba w parametrze jakos
    }
}
