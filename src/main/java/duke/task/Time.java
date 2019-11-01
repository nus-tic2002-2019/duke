package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Time extends Task {
    private LocalDateTime time;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    Time(String description, String timeString){
        super(description);
        time = LocalDateTime.parse(timeString, formatter);
    }
    String convertSaveTimeString(){
        return formatter.format(time);
    }
    String convertTimeString(){
        return DateTimeFormatter.ofPattern("MMM d yyyy HHmm").format(time);
    }

}
