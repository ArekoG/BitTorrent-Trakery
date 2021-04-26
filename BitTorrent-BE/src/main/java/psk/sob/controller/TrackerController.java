package psk.sob.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import psk.sob.dto.File;
import psk.sob.dto.Tracker;
import psk.sob.dto.User;
import psk.sob.service.TrackerService;

import java.util.List;

@RestController
@AllArgsConstructor
public class TrackerController {
    private TrackerService trackerService;

    @GetMapping("/trackers")
    public List<Tracker> getTrackers() {
        return trackerService.getTrackers();
    }

    @GetMapping("/trackers/{trackerId}/users")
    private List<User> getUsersInTracker(@PathVariable String trackerId) {
        return trackerService.getUsersInTracker(trackerId);
    }

    @GetMapping("/trackers/{trackerId}/files")
    private List<File> getFilesInTracker(@PathVariable String trackerId) {
        return trackerService.getFilesInTracker(trackerId);
    }
}
