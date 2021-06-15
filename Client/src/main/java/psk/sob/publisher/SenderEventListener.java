package psk.sob.publisher;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import psk.sob.entity.repository.DataTransferRepository;
import psk.sob.service.SenderThreadService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class SenderEventListener implements ApplicationListener<SenderEvent> {
    private final DataTransferRepository dataTransferRepository;

    @SneakyThrows
    @Override
    public void onApplicationEvent(SenderEvent senderEvent) {
        ExecutorService executorService = Executors.newFixedThreadPool(senderEvent.getFileDownloadInformation().size());
        for (int i = 0; i < senderEvent.getFileDownloadInformation().size(); i++) {
            int finalI = i;
            executorService.submit(new SenderThreadService(senderEvent.getFileDownloadInformation().get(finalI), senderEvent, dataTransferRepository));
        }
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
    }
}
