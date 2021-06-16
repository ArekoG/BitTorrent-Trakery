package psk.sob.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import psk.sob.entity.Tracker;
import psk.sob.entity.TrackerUsersList;
import psk.sob.entity.User;
import psk.sob.entity.repository.TrackerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class TrackerService {
    private TrackerRepository trackerRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public void broadcast(String login) {

        List<Tracker> trackers = trackerRepository.findAllByLoginAndStatus(login);

        for (Tracker tracker : trackers) {
            Integer userId = tracker.getTrackerUsersList()
                    .stream()
                    .filter(trackerUsersList -> !trackerUsersList.equals(login))
                    .map(TrackerUsersList::getUser)
                    .map(User::getId)
                    .findFirst().get();
            final Map<String, Integer> variables = new HashMap<>();
            variables.put("trackerId", tracker.getId());
            variables.put("userId", userId);

            restTemplate.postForObject("http://localhost:8080/bit-torrent/trackers/{trackerId}/users/{userId}/enable", Void.class, Object.class, variables);
        }
    }
}
