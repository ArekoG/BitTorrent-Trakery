package psk.sob.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDownloadInformation implements Serializable {
    private int userId;
    private int start;
    private int stop;
}
