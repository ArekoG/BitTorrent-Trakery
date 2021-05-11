package psk.sob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TrackerServer {

    public static void main(String[] args) {
        SpringApplication.run(TrackerServer.class, args);
    }
}
