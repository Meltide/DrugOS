package bin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss a");

    public void execute(String[] args) {
        System.out.println(now.format(fmt));
    }
}
