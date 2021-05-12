package psk.sob.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import psk.sob.dto.File;
import psk.sob.dto.Tracker;
import psk.sob.dto.User;
import psk.sob.service.TrackerService;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class TrackerController {
    private TrackerService trackerService;

    @GetMapping("/trackers")
    public List<Tracker> getTrackers() {
        return trackerService.getTrackers();
    }

    @GetMapping("/trackers/{trackerId}/users")
    private List<User> getUsersInTracker(@PathVariable int trackerId) {
        return trackerService.getUsersInTracker(trackerId);
    }


    @PostMapping("/trackers/{trackerId}/users/{userId}/enabled")
    public void enableUserOnTracker(@PathVariable int trackerId, @PathVariable int userId) {
        trackerService.enableUserOnTracker(trackerId,userId);
    }

}
