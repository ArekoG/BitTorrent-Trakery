package psk.sob.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import psk.sob.dto.File;
import psk.sob.dto.Tracker;
import psk.sob.dto.User;
import psk.sob.repository.TrackerRepository;
import psk.sob.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TrackerService {
    public List<Tracker> getTrackers() {
        return null;
    }

    public List<User> getUsersInTracker(String trackerId) {
        return null;
    }

    public List<File> getFilesInTracker(String trackerId) {
        return null;
    }
}
