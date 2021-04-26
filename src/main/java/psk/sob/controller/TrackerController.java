package psk.sob.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import psk.sob.dto.File;
import psk.sob.dto.Status;
import psk.sob.dto.Tracker;
import psk.sob.dto.User;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class TrackerController {
    @GetMapping("/trackers")
    public List<Tracker> getTrackers() {
        return Collections.singletonList(Tracker.builder()
                .id(UUID.randomUUID().toString())
                .trackerStatus(Status.DISABLE)
                .users(Collections.singletonList(User.builder()
                        .id(UUID.randomUUID().toString())
                        .userStatus(Status.ENABLE)
                        .build()))
                .build());
    }

    @GetMapping("/trackers/{trackerId}/users")
    private List<User> getUsersInTracker(@PathVariable String trackerId) {
        return null;
    }

    @GetMapping("/trackers/{trackerId}/files")
    private List<File> getFilesInTracker(@PathVariable String trackerId) {
        return null;
    }
}
