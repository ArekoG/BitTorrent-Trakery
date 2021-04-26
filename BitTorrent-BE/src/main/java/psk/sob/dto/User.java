package psk.sob.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class User {
    private String id;
    private Status userStatus;
    private List<File> files;
}

