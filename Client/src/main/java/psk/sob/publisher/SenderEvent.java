package psk.sob.publisher;

import lombok.Value;
import org.springframework.context.ApplicationEvent;
import psk.sob.dto.FileDownloadInformation;

import java.util.List;

@Value
public class SenderEvent extends ApplicationEvent {
    private List<FileDownloadInformation> fileDownloadInformation;
    private int userId;

    public SenderEvent(Object source, List<FileDownloadInformation> fileDownloadInformation, int userId) {
        super(source);
        this.fileDownloadInformation = fileDownloadInformation;
        this.userId = userId;
    }
}
