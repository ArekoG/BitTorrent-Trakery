package psk.sob.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class User {
    private Integer userId;
    private String userStatus;
    private String userLogin;
}

