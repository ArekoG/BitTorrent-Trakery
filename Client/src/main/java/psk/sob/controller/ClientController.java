package psk.sob.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import psk.sob.service.TrackerService;
import psk.sob.service.UserService;

@RestController
@AllArgsConstructor
public class ClientController {

    private UserService userService;
    private TrackerService trackerService;

    @PostMapping("/users/{login}/enable")
    public void enableUser(@PathVariable String login) {
        userService.enableUser(login);
        trackerService.broadcast(login);

    }

    @PostMapping("/users/{login}/disable")
    public void disableUser(@PathVariable String login) {
        userService.disableUser(login);
    }

    @PostMapping("/users/{userId}/is-alive")
    public ResponseEntity isUserAlive(@PathVariable int userId) {
        return userService.isUserAlive(userId);
    }
}
