package psk.sob.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import psk.sob.entity.User;
import psk.sob.entity.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

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


    public ResponseEntity isUserAlive(int userId) {
        Integer count = userRepository.countActiveUserByUserIdAndTrackerId(userId);
        if (count == 0) {
            return ResponseEntity.status(500)
                    .build();
        }
        return ResponseEntity.status(200)
                .build();

    }
}
