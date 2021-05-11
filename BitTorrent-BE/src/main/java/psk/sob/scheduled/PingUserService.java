package psk.sob.scheduled;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PingUserService {
    //inject user repository
    @Scheduled(fixedRate = 30000)
    public void pingUsers() {
        //1.Get users list
        //2.in loop ping to user, if user return 200 is ok, if 500 change user status to disable in DB

    }
}
