package psk.sob.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class File {
    private String id;
    private double size;
}
