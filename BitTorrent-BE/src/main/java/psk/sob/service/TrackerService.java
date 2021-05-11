package psk.sob.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import psk.sob.dto.File;
import psk.sob.dto.Tracker;
import psk.sob.dto.User;
import psk.sob.entity.TrackerUsersList;
import psk.sob.repository.TrackerUserListRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TrackerService {
    private TrackerUserListRepository trackerUserListRepository;
    public List<Tracker> getTrackers() {
        return null;
    }

    public List<User> getUsersInTracker(String trackerId) {
        return null;
    }

    public List<File> getFilesInTracker(String trackerId) {
        return null;
    }

    public void enableUserOnTracker(int trackerId, int userId) {
        TrackerUsersList trackerIdAndUserId = trackerUserListRepository.findByTrackerIdAndUserId(trackerId, userId);
        trackerIdAndUserId.setStatus("enable");
        trackerUserListRepository.save(trackerIdAndUserId);
    }
}
