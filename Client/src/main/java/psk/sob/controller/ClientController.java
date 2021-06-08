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

    @PostMapping("/users/{userId}/files/{fileName}/download")
    public void downloadFile(@PathVariable int userId, @PathVariable String fileName) {
        userService.downloadFile(userId, fileName);
    }

    @PostMapping("/users/{userId}/start-download")
    public void startDownloading(@RequestBody List<FileDownloadInformation> fileDownloadInformation, @PathVariable int userId) {
        //   userService.downloadFile(userId, fileName);
    }
}
