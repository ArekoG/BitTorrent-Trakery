package psk.sob.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Tracker {
    private int trackerId;
    private String trackerName;
    private String trackerStatus;
    private int numberOfUsers;
}
