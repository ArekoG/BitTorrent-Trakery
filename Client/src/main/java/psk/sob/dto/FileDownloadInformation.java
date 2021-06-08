package psk.sob.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FileDownloadInformation {
    private int userId;
    private String fileName;
    private int start;
    private int stop;
}
