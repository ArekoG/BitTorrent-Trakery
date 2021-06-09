package psk.sob.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder
public class FileDownloadInformation implements Serializable {
    private int userId;
    private int start;
    private int stop;
}
