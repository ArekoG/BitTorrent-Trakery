package psk.sob.controller;


import io.swagger.models.Response;
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
public class TestController {

    private UserService userService;
    private TrackerService trackerService;
    @PostMapping("/users/{login}/enable")
    public void enableUser(@PathVariable String login) {
        //1.enable user in DB
        userService.enableUser(login);
        //2..Get trackers list
        //3.Tell trackers I am online
        trackerService.broadcast(login);

    }

    @PostMapping("/users/{login}/disable")
    public void disableUser(@PathVariable String login) {
        //1.disable user
    }

    @GetMapping("/users/{login}/is-alive")
    public ResponseEntity isUserAlive(@PathVariable String login) {
        //1.check if user is active
    /*    2. if so return 500
        return ResponseEntity.status(500)
                .build();*/
    // if 200
       // return ResponseEntity.status(200)
      //          .build();
        return null;
    }
}
