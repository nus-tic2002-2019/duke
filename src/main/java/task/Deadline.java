package task;

import java.time.LocalDateTime;

/**
 * Represent data structure to for Deadline command.
 * It takes in a description and due date with time.
 */
public class Deadline extends Task{
    private String description;
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by){
        super(description);
        this.by = by;
    }

    public LocalDateTime getDate(){
        return by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
