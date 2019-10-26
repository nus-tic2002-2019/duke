package task;

import java.time.LocalDateTime;

/**
 * Represent data structure to for Event command.
 * It takes in a description and appointment date with time.
 */
public class Event extends Task{
    private String description;
    private LocalDateTime at;

    public Event(String description, LocalDateTime at){
        super(description);
        this.at = at;
    }

    public LocalDateTime getDate(){
        return at;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}
