package psk.sob.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import psk.sob.dto.File;
import psk.sob.dto.FileDownloadInformation;
import psk.sob.dto.User;
import psk.sob.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{userId}")
    public List<File> getFiles(@PathVariable String userId) {
        return userService.getFiles(userId);
    }

    @PostMapping("/users/{userId}/enable")
    public void enableUser(@PathVariable int userId) {
        userService.enableUser(userId);
    }

    @PostMapping("/users/{userId}/disable")
    public void disableUser(@PathVariable int userId) {
        userService.disableUser(userId);
    }

    @PostMapping("/users/files/{fileId}/download/{trackerId}")
    public List<FileDownloadInformation> divideFileAndStartDownloading(@PathVariable int fileId, @PathVariable int trackerId) {
        return userService.divideFileAndStartDownloading(fileId, trackerId);
    }
}
