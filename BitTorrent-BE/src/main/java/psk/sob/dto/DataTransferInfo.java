package psk.sob.dto;

import lombok.Builder;
import lombok.Value;
import psk.sob.entity.User;

import java.util.List;

@Value
@Builder
public class DataTransferInfo {
    private int dataTransferId;
    private int trackerId;
    private psk.sob.entity.User userTo;
    private List<User> userFromList;
}
