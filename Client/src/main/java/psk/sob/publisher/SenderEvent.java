package psk.sob.publisher;

import lombok.Value;
import org.springframework.context.ApplicationEvent;
import psk.sob.dto.FileDownloadInformation;

import java.util.List;

@Value
public class SenderEvent extends ApplicationEvent {
    private List<FileDownloadInformation> fileDownloadInformation;
    private int userId;
    private int fileId;
    private int dataTransferId;

    public SenderEvent(Object source, List<FileDownloadInformation> fileDownloadInformation, int userId, int fileId, int dataTransferId) {
        super(source);
        this.fileDownloadInformation = fileDownloadInformation;
        this.userId = userId;
        this.fileId = fileId;
        this.dataTransferId = dataTransferId;
    }
}
