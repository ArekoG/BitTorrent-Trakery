package psk.sob.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import psk.sob.dto.File;
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

    @PostMapping("/users/{userId}/files/{fileName}/download/{trackerId}")
    public void divideFileAndStartDownloading(@PathVariable int userId, @PathVariable String fileName, @PathVariable int trackerId) {
        userService.divideFileAndStartDownloading(userId, fileName, trackerId);
    }

}
