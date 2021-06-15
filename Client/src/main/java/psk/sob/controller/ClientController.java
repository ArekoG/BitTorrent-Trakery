package psk.sob.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import psk.sob.dto.FileDownloadInformation;
import psk.sob.service.TrackerService;
import psk.sob.service.UserService;

import java.util.List;

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

    @PostMapping("/trackers/{trackerId}/users/{login}/assign")
    public void assignUser(@PathVariable int trackerId, @PathVariable String login) {
        userService.assignUserToTheTracker(trackerId, login);
    }

    @PostMapping("/users/{userId}/is-alive")
    public ResponseEntity isUserAlive(@PathVariable int userId) {
        return userService.isUserAlive(userId);
    }

    @PostMapping("/users/{userId}/files/{fileId}/download/{trackerId}")
    public void downloadFile(@PathVariable int userId, @PathVariable int fileId, @PathVariable int trackerId) {
        userService.downloadFile(userId, fileId, trackerId);
    }

    @PostMapping("/users/{userId}/receive/{data}")
    public void receive(@PathVariable int userId, @PathVariable String data) {
        System.out.println(data);
    }
}
