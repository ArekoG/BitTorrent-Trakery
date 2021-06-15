package psk.sob.publisher;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import psk.sob.dto.FileDownloadInformation;

import java.util.List;

@AllArgsConstructor
@Service
public class SpringEventPublisher {
    private ApplicationEventPublisher applicationEventPublisher;

    public void startDownloading(List<FileDownloadInformation> fileDownloadInformation, int userId, int fileId, Integer dataTransferId) {
        SenderEvent senderEvent = new SenderEvent(this, fileDownloadInformation, userId, fileId, dataTransferId);
        applicationEventPublisher.publishEvent(senderEvent);
    }
}
