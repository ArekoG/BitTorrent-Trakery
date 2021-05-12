package psk.sob.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import psk.sob.dto.File;
import psk.sob.dto.User;
import psk.sob.entity.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

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
}
