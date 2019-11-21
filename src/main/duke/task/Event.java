package main.duke.task;

import main.duke.exception.DukeMissingDescException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime start_endTime;

    public LocalDateTime getStart_endTime() {
        return start_endTime;
    }

    public void setStart_endTime(String start_endTime){
        this.start_endTime = Parser.parseDateTimeStr(start_endTime);
    }

    public Event(String description, String start_endTime) throws DukeMissingDescException {
        super(description);
        setStart_endTime(start_endTime);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), getStart_endTime());
    }
}
