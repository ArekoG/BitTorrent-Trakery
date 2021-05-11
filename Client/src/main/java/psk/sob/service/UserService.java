package psk.sob.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import psk.sob.entity.User;
import psk.sob.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public void enableUser(String login) {
        User user = userRepository.findByLogin(login);
        user.setStatus("enable");
        userRepository.save(user);
    }
}
