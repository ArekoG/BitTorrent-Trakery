package psk.sob.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import psk.sob.dto.File;
import psk.sob.dto.FileDownloadInformation;
import psk.sob.dto.User;
import psk.sob.entity.repository.FileRepository;
import psk.sob.entity.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private FileRepository fileRepository;

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

    public List<FileDownloadInformation> divideFileAndStartDownloading(int fileId, int trackerId) {
        List<Integer> ownersOfFile = userRepository.listOfUsersWhoHaveTheFile(fileId, trackerId);
        List<FileDownloadInformation> fileDownloadInformation = new ArrayList<>();
        int start = 0;
        int stop;
        int fileSize = fileRepository.findById(fileId).getSize();
        for (int id : ownersOfFile) {
            stop = fileSize / ownersOfFile.size() + start;
            fileDownloadInformation.add(FileDownloadInformation.builder()
                    .userId(id)
                    .start(start)
                    .stop(stop - 1)
                    .build());
            start = stop;
        }
        return fileDownloadInformation;
    }
}
