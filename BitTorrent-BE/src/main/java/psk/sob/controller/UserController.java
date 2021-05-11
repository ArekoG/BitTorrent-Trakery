package psk.sob.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import psk.sob.dto.File;
import psk.sob.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/users/{userId}")
    public List<File> getFiles(@PathVariable String userId) {
        return userService.getFiles(userId);
    }


}
