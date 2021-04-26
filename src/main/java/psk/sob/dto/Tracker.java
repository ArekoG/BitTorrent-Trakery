package psk.sob.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Tracker {
    private String id;
    private Status trackerStatus;
    private List<User> users;
}
