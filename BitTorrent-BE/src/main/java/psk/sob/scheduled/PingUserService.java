package psk.sob.scheduled;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import psk.sob.dto.DataTransferInfo;
import psk.sob.entity.DataTransfer;
import psk.sob.entity.Tracker;
import psk.sob.entity.TrackerUsersList;
import psk.sob.entity.User;
import psk.sob.entity.repository.DataTransferRepository;
import psk.sob.entity.repository.TrackerRepository;
import psk.sob.entity.repository.TrackerUserListRepository;
import psk.sob.entity.repository.UserRepository;
import psk.sob.service.DataTransferService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class PingUserService {
    private UserRepository userRepository;
    private TrackerRepository trackerRepository;
    private TrackerUserListRepository trackerUserListRepository;
    private DataTransferRepository dataTransferRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private DataTransferService dataTransferService;

    @Scheduled(fixedRate = 30000)
    public void pingUsers() {
        List<Tracker> trackers = trackerRepository.findAll();
        for (Tracker tracker : trackers) {
            List<User> users = userRepository.findByTrackerId(tracker.getId());
            for (User user : users) {
                final Map<String, Integer> variables = new HashMap<>();
                variables.put("userId", user.getId());
                try {
                    restTemplate.postForObject("http://localhost:8081/client/users/{userId}/is-alive", Void.class, Object.class, variables);
                } catch (Exception ex) {
                    TrackerUsersList trackerUsersList = trackerUserListRepository.findByTrackerIdAndUserId(tracker.getId(), user.getId());
                    trackerUsersList.setStatus("disable");
                    trackerUserListRepository.save(trackerUsersList);
                }
            }
        }
    }

    @Scheduled(fixedRate = 1000)
    public void pingUsersInvolvedInDataTransfer() {
        List<DataTransfer> dataTransfersActive = dataTransferRepository.findAllActive();
        if(!dataTransfersActive.isEmpty()){
            List<DataTransferInfo> dataTransferInfoList = new ArrayList<>();
            for(DataTransfer dataTransfer : dataTransfersActive) {
                List<User> usersFrom = new ArrayList<>();
                dataTransfer.getUsersFrom().forEach(userFrom -> usersFrom.add(userRepository.findById(userFrom).orElseThrow(RuntimeException::new)));
                dataTransferInfoList.add(
                        DataTransferInfo.builder()
                                .dataTransferId(dataTransfer.getId())
                                .userTo(userRepository.findById(dataTransfer.getUser().getId()).orElseThrow(RuntimeException::new))
                                .userFromList(usersFrom)
                                .trackerId(dataTransfer.getTracker().getId()).build());
            }
            dataTransferInfoList.forEach(dataTransferInfo -> {
                //userTo
                try {
                    restTemplate.postForObject("http://localhost:8081/client/users/"
                            + dataTransferInfo.getUserTo().getId() +"/is-alive", Void.class, Object.class);
                    restTemplate.postForObject("http://localhost:8081/client/trackers/"
                            + dataTransferInfo.getTrackerId() +"/users/"
                            + dataTransferInfo.getUserTo().getId() +"/is-alive", Void.class, Object.class);
                    restTemplate.postForObject("http://localhost:8080/bit-torrent/trackers/"
                            + dataTransferInfo.getTrackerId() +"/is-alive", Void.class, Object.class);
                } catch (Exception ex) {
                    // Jeżeli user pobierający się wyłączy to przerywamy pobieranie
                    // Jeżeli user pobierający zostanie wyłączony w trackerze to przerywamy pobieranie
                    dataTransferService.disableTransfer(dataTransferInfo.getDataTransferId());
                }
                //userFrom
                for (User userFrom : dataTransferInfo.getUserFromList()){
                    final Map<String, Integer> userFromVariables = new HashMap<>();
                    userFromVariables.put("userId", userFrom.getId());
                    try {
                        restTemplate.postForObject("http://localhost:8081/client/users/{userId}/is-alive", Void.class, Object.class, userFromVariables);
                    } catch (Exception ex) {
                        // Jeżeli któryś z userów od których jest pobierany plik nagle się wyłączy trzeba przerzucić pobieranie całości na pozostałych.
                    }
                }
            });
        }
    }

}
