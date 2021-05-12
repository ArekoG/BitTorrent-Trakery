package psk.sob.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import psk.sob.entity.TrackerUsersList;
import psk.sob.entity.User;
import psk.sob.entity.repository.TrackerRepository;
import psk.sob.entity.repository.TrackerUserListRepository;
import psk.sob.entity.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private TrackerRepository trackerRepository;
    private TrackerUserListRepository trackerUserListRepository;

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
}
