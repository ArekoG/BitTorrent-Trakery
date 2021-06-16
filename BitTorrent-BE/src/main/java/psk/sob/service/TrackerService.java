package psk.sob.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import psk.sob.dto.File;
import psk.sob.dto.Tracker;
import psk.sob.dto.User;
import psk.sob.entity.TrackerUsersList;
import psk.sob.entity.repository.TrackerRepository;
import psk.sob.entity.repository.TrackerUserListRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TrackerService {
    private TrackerUserListRepository trackerUserListRepository;
    private TrackerRepository trackerRepository;

    public List<Tracker> getTrackers() {
        List<Tracker> trackerListAfterMapping = new ArrayList<>();
        List<psk.sob.entity.Tracker> trackerList = trackerRepository.findAll();
        trackerList.forEach(i -> trackerListAfterMapping.add(
                Tracker.builder()
                        .trackerId(i.getId())
                        .trackerName(i.getName())
                        .trackerStatus(i.getStatus())
                        .numberOfUsers(trackerUserListRepository.findAllByTrackerId(i.getId()).size()).build()));
        return trackerListAfterMapping;
    }

    public List<User> getUsersInTracker(int trackerId) {
        List<User> trackesUsersListAfterMapping = new ArrayList<>();
        List<TrackerUsersList> trackerUsersList = trackerUserListRepository.findAllByTrackerId(trackerId);
        trackerUsersList.forEach(i -> trackesUsersListAfterMapping.add(
                User.builder()
                        .userId(i.getUser().getId())
                        .userStatus(i.getStatus())
                        .userLogin(i.getUser().getLogin())
                        .build()));
        return trackesUsersListAfterMapping;
    }

    public List<File> getFilesInTracker(String trackerId) {
        return null;
    }

    @Transactional
    public void enableUserOnTracker(int trackerId, int userId) {
        TrackerUsersList trackerIdAndUserId = trackerUserListRepository.findByTrackerIdAndUserId(trackerId, userId);
        trackerIdAndUserId.setStatus("enable");
        trackerUserListRepository.save(trackerIdAndUserId);

    }

    @Transactional
    public void enableTracker(int trackerId) {
        psk.sob.entity.Tracker tracker = trackerRepository.findById(trackerId)
                .orElseThrow(RuntimeException::new);
        tracker.setStatus("enable");
        trackerRepository.save(tracker);
    }

    @Transactional
    public void disableTracker(int trackerId) {
        psk.sob.entity.Tracker tracker = trackerRepository.findById(trackerId)
                .orElseThrow(RuntimeException::new);
        tracker.setStatus("disable");
        trackerRepository.save(tracker);
    }

    @Transactional
    public void disableUserOnTracker(int trackerId, int userId) {
        TrackerUsersList trackerIdAndUserId = trackerUserListRepository.findByTrackerIdAndUserId(trackerId, userId);
        trackerIdAndUserId.setStatus("disable");
        trackerUserListRepository.save(trackerIdAndUserId);
    }
}
