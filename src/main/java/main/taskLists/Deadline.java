package main.taskLists;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline<T> extends Task {

    private T by;


    public Deadline(String description, T by) {
        super(description);
        this.by = by;
    }

    public T getBy() {
        return this.by;
    }


    private String convert(LocalDate input) {
        LocalDate clone = input;
        return clone.format(DateTimeFormatter.ofPattern("MMMM dd yyyy"));
    }

    @Override
    public String toString() {
        try {
            return "[D]" + "[" + this.getStatusIcon() + "] " + super.toString() + " (by: " + convert((LocalDate) by) + ")";
        } catch (Exception e) {
            return "[D]" + "[" + this.getStatusIcon() + "] " + super.toString() + " (by: " + this.by + ")";
        }
    }

}

